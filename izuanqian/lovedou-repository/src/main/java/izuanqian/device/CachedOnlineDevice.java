package izuanqian.device;

import izuanqian.DeviceType;
import lombok.Data;

@Data
public class CachedOnlineDevice {

    private String code;
    private String pushCode;
    private DbDeviceInformation.DeviceState state;
    private DeviceType type;
}
