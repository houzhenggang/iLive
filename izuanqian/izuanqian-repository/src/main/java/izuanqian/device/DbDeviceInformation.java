package izuanqian.device;

import izuanqian.DeviceType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author sanlion do
 */
@Data
@NoArgsConstructor
public class DbDeviceInformation implements Serializable{

//    private DeviceType deviceType;
    private String deviceCode;

    public DbDeviceInformation(DeviceType deviceType, String deviceCode) {
//        this.deviceType = deviceType;
        this.deviceCode = deviceCode;
    }
}
