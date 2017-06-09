package izuanqian.user.domain;

import izuanqian.DBType;
import izuanqian.user.dbo.CachedProfile;
import izuanqian.user.dbo.DbProfile;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

@Data
@Slf4j
public class UserProfile {

    private String id;
    private long code;
    private String mobile;
    private String nick;
    private CachedProfile.Gender gender;
    private String deviceCode;

    public UserProfile(DbProfile dbProfile) {
        log.warn(dbProfile.toString());
        BeanUtils.copyProperties(dbProfile, this);
        log.warn("copy after, {}",dbProfile.toString());
        this.gender = DBType.type(CachedProfile.Gender.class, dbProfile.getGender());
    }
}
