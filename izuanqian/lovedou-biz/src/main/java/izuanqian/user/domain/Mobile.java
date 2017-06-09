package izuanqian.user.domain;

import izuanqian.user.dbo.DbProfile;
import izuanqian.user.dbo.DboMobile;
import lombok.Data;

@Data
public class Mobile {

    private String id;
    private String mobile;

    public Mobile(DboMobile dboMobile) {

        this.id = dboMobile.getId();
        this.mobile = dboMobile.getMobile();
    }

    public Mobile(UserProfile userProfile) {
        this.id = userProfile.getId();
        this.mobile = userProfile.getMobile();
    }
}
