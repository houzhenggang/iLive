package izuanqian.user.dbo;

import lombok.Data;

@Data
public class DbProfile {

    private String id;
    private long code;
    private String mobile;
    private String nick;
    private int gender;
    private String deviceCode;
}
