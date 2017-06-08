package izuanqian.user.dbo;

import lombok.Data;

@Data
public class CachedProfile {

    private String id;
    private long code;
    private String mobile;
    private String nick;
    private Gender gender;
    private String deviceCode;

    public enum Gender {

        M, F
    }
}
