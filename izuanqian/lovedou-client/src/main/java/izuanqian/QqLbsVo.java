package izuanqian;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import java.io.Serializable;

/**
 * Created by PC on 2017/3/20.
 */
@Slf4j
@Data
@NoArgsConstructor
public class QqLbsVo<T> implements Serializable {

    public static final QqLbsVo Ok = new QqLbsVo();

    private static final long serialVersionUID = 8904951115784291463L;

    private int status;
    private String message;
    T result;

    public boolean isOk() {
        return this.status == 0;
    }

    public boolean isFail() {
        return !isOk();
    }

    public T value(int failCode){
        requireOk(failCode);
        return result;
    }

    public T value(int failCode, String failMessage){
        requireOk(failCode, failMessage);
        return result;
    }

    /**
     * 若请求失败，则抛出异常bizException
     *
     * @param failCode
     * @param failMessage
     * @see BizException
     */
    public QqLbsVo<T> requireOk(int failCode, String failMessage) {
        if (isFail()) {
            log.warn(getMessage());
            throw new BizException(failCode, failMessage);
        }
        return this;
    }

    /**
     * 若请求失败，则抛出异常bizException
     *
     * @param failCode
     * @see BizException
     * @see QqLbsVo#requireOk(int, String)
     */
    public QqLbsVo<T> requireOk(int failCode) {
        return requireOk(failCode, "");
    }
}
