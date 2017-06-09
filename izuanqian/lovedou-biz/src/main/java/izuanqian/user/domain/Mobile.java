package izuanqian.user.domain;

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
