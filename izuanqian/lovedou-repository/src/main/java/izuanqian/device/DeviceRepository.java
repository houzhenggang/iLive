package izuanqian.device;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import izuanqian.DeviceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static izuanqian.Key.__;

/**
 * @author sanlion do
 */
@Slf4j
@Component
public class DeviceRepository {



    /*
    以hash的结构进行存储

    device:online:code =>
            type
            code
            back
     */

    @Autowired
    @Qualifier("tokenRedisTemplate")
    private StringRedisTemplate tokenRedisTemplate;
    @Autowired private DeviceMapper deviceMapper;

    /**
     * save device information
     *
     * @param deviceType
     * @param deviceCode
     * @param pushDeviceCode
     */
    public void save(
            DeviceType deviceType, String deviceCode, String pushDeviceCode) {

        // todo db不保存即时性信息：pushCode，state
        boolean hasAny = deviceMapper.hasAny(deviceCode);
        if (!hasAny) {
            deviceMapper.saveDevice(deviceType.getCode(), deviceCode);

            // todo 保存在线设备信息
            String key = "device:online";
            tokenRedisTemplate.opsForHash()
                    .put(
                            key,
                            deviceCode,
                            new Gson().toJson(
                                    new CachedOnlineDevice() {{
                                        setCode(deviceCode);
                                        setPushCode(pushDeviceCode);
                                        setState(DbDeviceInformation.DeviceState.Foreground);
                                        setType(deviceType);
                                    }}));
        }
    }

    public void updateBackState(String deviceCode, DbDeviceInformation.DeviceState state) {
        String key = "device:online";
        HashOperations<String, String, String> hash = tokenRedisTemplate.opsForHash();
        String value = hash.get(key, deviceCode);
        CachedOnlineDevice cachedOnlineDevice = new Gson().fromJson(value, CachedOnlineDevice.class);
        if (Objects.isNull(cachedOnlineDevice)) {
            return;
        }
        cachedOnlineDevice.setState(state);
        hash.put(key, deviceCode, new Gson().toJson(cachedOnlineDevice));
    }

    /**
     * 仅查询在线设备信息
     *
     * @param deviceCode
     * @return
     */
    public DbDeviceInformation getOnlineDevice(String deviceCode) {
        String key = "device:online";
        HashOperations<String, String, String> hash = tokenRedisTemplate.opsForHash();
        String value = hash.get(key, deviceCode);
        CachedOnlineDevice cachedOnlineDevice = new Gson().fromJson(value, CachedOnlineDevice.class);
        return Objects.nonNull(cachedOnlineDevice) ? new DbDeviceInformation(cachedOnlineDevice) : null;
    }

    public void bindCurrentMobile(String deviceCode, long mobileId) {
        HashOperations<String, String, String> hash = tokenRedisTemplate.opsForHash();
        String key = __("device:online:{0}", deviceCode);
        hash.put(key, "mobile", String.valueOf(mobileId));
    }

    public Long getCurrentMobileId(String deviceCode) {
        String key = __("device:online:{0}", deviceCode);
        HashOperations<String, String, String> hash = tokenRedisTemplate.opsForHash();
        String mobileIdString = hash.get(key, "mobile");
        return !Strings.isNullOrEmpty(mobileIdString) ? Long.parseLong(mobileIdString) : null;
    }
}
