package izuanqian;

import izuanqian.user.domain.Mobile;
import lombok.Data;

/**
 * @author sanlion do
 */
@Data
public class Token {

    private String token;
    private DeviceType deviceType;
    private String deviceCode;

    public Token(String token, DeviceType deviceType, String deviceCode) {
        this.token = token;
        this.deviceType = deviceType;
        this.deviceCode = deviceCode;
    }
}
