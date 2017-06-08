package izuanqian.user.dbo;

import lombok.Data;

@Data
public class DbUserProfile {

    private long id;
    private String code;
    private String mobile;
    private String nick;
    private int gender;
    private String deviceCode;
}
