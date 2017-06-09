package izuanqian.user.dbo;

import lombok.Data;

@Data
public class UntreatedProfile {

    private long deviceId;
    private String avatar;
    private String nick;
    private int gender;
}
