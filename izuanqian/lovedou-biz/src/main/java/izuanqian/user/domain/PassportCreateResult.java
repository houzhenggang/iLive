package izuanqian.user.domain;

import lombok.Getter;

/**
 * @author sanlion do
 */
@Getter
public class PassportCreateResult {

    private String id;
    private long code;

    public PassportCreateResult(String id, long code) {
        this.id = id;
        this.code = code;
    }
}
