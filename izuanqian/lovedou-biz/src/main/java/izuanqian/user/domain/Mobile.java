package izuanqian.user.domain;

import izuanqian.user.dbo.DboMobile;
import lombok.Data;

@Data
public class Mobile {

    private long id;
    private String mobile;

    public Mobile(DboMobile dboMobile) {

        this.id = dboMobile.getId();
        this.mobile = dboMobile.getMobile();
    }
}
