package izuanqian;

import lombok.Getter;

/**
 * Created by root on 17-3-6.
 */
public class BizException extends RuntimeException {

    @Getter
    private int code;

    public BizException(int code, String message) {
        super(message);
        this.code = code;
    }
}
