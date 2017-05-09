package izuanqian.user.dbo;

import izuanqian.DeviceType;
import lombok.Builder;
import lombok.Data;

/**
 * @author sanlion do
 */
@Data
@Builder
public class DbUserProfile {

    private String id;
    private long code;
    private DeviceType deviceType;
    private String deviceCode;
    private String pushDeviceCode;
}
