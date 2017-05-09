package izuanqian.api.token.o.vo;

import izuanqian.DeviceType;
import izuanqian.Token;
import lombok.Data;

/**
 * Created by PC on 2017/4/5.
 */
@Data
public class TokenVo {

    private String token;
    private DeviceType deviceType;
    private String deviceCode;

    public TokenVo(Token token) {
        this.token = token.getToken();
        this.deviceCode = token.getDeviceCode();
        this.deviceCode = token.getDeviceCode();
    }
}
