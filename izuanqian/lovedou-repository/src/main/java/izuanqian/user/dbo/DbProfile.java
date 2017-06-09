package izuanqian.user.dbo;

import lombok.Data;

@Data
public class DbProfile {

    private long id;
    private String mobile;
    private String nick;
    private int gender;
    private String deviceCode;
}
