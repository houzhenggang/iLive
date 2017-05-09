package izuanqian.response;

import lombok.Data;

/**
 * Created by root on 17-3-5.
 */
@Data
public class Fail extends ApiResponse {

    public Fail(boolean ok, int code, String message) {
        super();
        this.setOk(ok);
        this.setCode(code);
        this.setMessage(message);
    }
}
