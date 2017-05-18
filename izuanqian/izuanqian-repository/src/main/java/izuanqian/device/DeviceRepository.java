package izuanqian.device;

import izuanqian.DeviceType;
import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

import static izuanqian.Key.__;

/**
 * @author sanlion do
 */
@Slf4j
@Component
public class DeviceRepository {

    @Autowired private Ignite ignite;

    /**
     * save device information
     *
     * @param deviceType
     * @param deviceCode
     */
    public void save(DeviceType deviceType, String deviceCode) {
        IgniteCache<String, DbDeviceInformation> deviceCache = ignite.getOrCreateCache("device");
        deviceCache.put(
                __("device:{0}", deviceCode),
                new DbDeviceInformation(deviceType, deviceCode));
    }

    public DbDeviceInformation get(String deviceCode) {
        String key = __("device:{0}", deviceCode);
        IgniteCache<String, DbDeviceInformation> deviceCache = ignite.getOrCreateCache("device");
        return deviceCache.get(key);
    }
}
