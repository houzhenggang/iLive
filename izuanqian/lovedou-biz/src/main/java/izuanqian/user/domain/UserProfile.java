package izuanqian.user.domain;

import izuanqian.DBType;
import izuanqian.user.dbo.CachedProfile;
import izuanqian.user.dbo.DbUserProfile;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class UserProfile {

    private long id;
    private String mobile;
    private String nick;
    private CachedProfile.Gender gender;
    private String deviceCode;
    private boolean active;

    public UserProfile(DbUserProfile dbUserProfile) {
        BeanUtils.copyProperties(dbUserProfile, this);
        this.gender = DBType.type(CachedProfile.Gender.class, dbUserProfile.getGender());
    }
}
