package izuanqian.device;

import izuanqian.DeviceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * save device information
     *
     * @param deviceType
     * @param deviceCode
     */
    public void save(DeviceType deviceType, String deviceCode) {
        HashOperations<String, String, Object> hash = tokenRedisTemplate.opsForHash();
        String key = __("device:online:{0}", deviceCode);
        hash.putAll(key, new HashMap<String, Object>() {{
            put("type", deviceType.name());
            put("code", deviceCode);
        }});
    }

    public void updateBackState(String deviceCode, boolean isBack) {
        HashOperations<String, String, Object> hash = tokenRedisTemplate.opsForHash();
        String key = __("device:online:{0}", deviceCode);
        hash.put(key, "back", isBack);
    }

    public DbDeviceInformation get(String deviceCode) {
        HashOperations<String, String, Object> hash = tokenRedisTemplate.opsForHash();
        String key = __("device:online:{0}", deviceCode);
        Map<String, Object> entries = hash.entries(key);
        return toDevice(entries);
    }

    /**
     * 将map转化为对象
     *
     * @param entries
     * @return
     */
    private DbDeviceInformation toDevice(Map<String, Object> entries) {
        DbDeviceInformation device = new DbDeviceInformation();
        entries.forEach(
                (key, value) -> {
                    switch (key) {
                        case "type":
                            device.setDeviceType(DeviceType.valueOf(key));
                            break;
                        case "code":
                            device.setDeviceCode(String.valueOf(value));
                            break;
                        case "back":
                            device.setBack((Boolean) value);
                            break;
                    }
                });
        return device;
    }
}
