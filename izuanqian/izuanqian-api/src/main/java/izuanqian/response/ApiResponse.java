package izuanqian.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by root on 17-3-5.
 */
@Data
public class ApiResponse {

    private static final NullObj Null = new NullObj();

    @ApiModelProperty("ok?") private boolean ok = true;
    @ApiModelProperty("response code") private int code = 0;
    @ApiModelProperty("response message") private String message;
    @ApiModelProperty("object data") private Object obj = Null;

    @Data
    public static  class NullObj implements Serializable {
        private String __ = "please ignore the business data.";
    }
}
