package izuanqian.device;

import izuanqian.DeviceType;
import lombok.Builder;
import lombok.Data;

/**
 * @author sanlion do
 */
@Data
@Builder
public class DbDeviceInformation {

    private DeviceType deviceType;
    private String deviceCode;
}
