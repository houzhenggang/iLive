package izuanqian.device;

import com.google.gson.Gson;
import izuanqian.DeviceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

import static izuanqian.Key.__;

/**
 * @author sanlion do
 */
@Component
public class DeviceRepository {

    @Autowired @Qualifier("db1") private StringRedisTemplate template;

    /**
     * save device information
     *
     * @param deviceType
     * @param deviceCode
     */
    public void save(DeviceType deviceType, String deviceCode) {
        template.opsForHash().put(
                __("device"),
                deviceCode,
                new Gson().toJson(DbDeviceInformation.builder().deviceCode(deviceCode).deviceType(deviceType).build()));
    }

    public Optional<DbDeviceInformation> get(String deviceCode) {
        Object value = template.opsForHash().get("device", deviceCode);
        return !Objects.isNull(value) ? Optional.of(
                new Gson().fromJson(String.valueOf(value), DbDeviceInformation.class)
        ) : Optional.empty();
    }
}
