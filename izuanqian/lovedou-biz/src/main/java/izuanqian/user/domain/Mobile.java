package izuanqian.user.domain;

import izuanqian.user.dbo.DbProfile;
import izuanqian.user.dbo.DboMobile;
import lombok.Data;

@Data
public class Mobile {

    private long id;
    private String mobile;


    public Mobile(UserProfile userProfile) {
        this.id = userProfile.getId();
        this.mobile = userProfile.getMobile();
    }
}
