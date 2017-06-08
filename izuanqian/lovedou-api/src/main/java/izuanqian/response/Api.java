package izuanqian.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by root on 17-3-5.
 */
@Data
public class Api {

    private static final NullObj Null = new NullObj();

    @ApiModelProperty("ok?") private boolean ok = true;
    @ApiModelProperty("response code") private int code = 0;
    @ApiModelProperty("response message") private String message;
    @ApiModelProperty("object data") private Object obj = Null;

    @Data
    public static  class NullObj implements Serializable {
        private String __ = "please ignore the business data.";
    }

    /**
     * Created by root on 17-3-5.
     */
    @Data
    public static class Fail extends Api {

        public Fail(boolean ok, int code, String message) {
            super();
            this.setOk(ok);
            this.setCode(code);
            this.setMessage(message);
        }
    }

    /**
     * Created by root on 17-3-5.
     */
    @Data
    @NoArgsConstructor
    public static class Ok extends Api {

        public Ok(String message) {
            super();
            this.setMessage(message);
        }

        public Ok(String message, Object obj) {
            this(message);
            this.setObj(obj);
        }


    }
}
