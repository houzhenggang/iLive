package izuanqian.device;

import izuanqian.DeviceType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author sanlion do
 */
@Data
@NoArgsConstructor
public class DbDeviceInformation implements Serializable{

    private DeviceType deviceType;
    private String deviceCode;
    private String pushDeviceCode;
    private boolean back; // 是否在后台运行

    public DbDeviceInformation(DeviceType deviceType, String deviceCode) {
        this.deviceType = deviceType;
        this.deviceCode = deviceCode;
        this.back = false;
    }
}
