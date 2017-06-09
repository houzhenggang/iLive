package izuanqian.user.dbo;

import lombok.Data;

@Data
public class DbUntreatedProfile {

    private String avatar;
    private String nick;
    private int gender;
    private int cityCode;
    private int countyCode;
    private long deviceId;
}
