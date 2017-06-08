package izuanqian.user.dbo;

import lombok.Data;
import lombok.Getter;

@Data
public class CachedProfile {

    private String id;
    private long code;
    private String mobile;
    private String nick;
    private Gender gender;
    private String deviceCode;

    public enum Gender {

        M(1), F(2);
        @Getter private int code;

        Gender(int code) {
            this.code = code;
        }
    }
}
