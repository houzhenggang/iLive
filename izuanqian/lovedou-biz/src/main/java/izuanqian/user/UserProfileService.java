package izuanqian.user;

import izuanqian.user.dbo.DbUserProfile;
import izuanqian.user.domain.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author sanlion do
 */
@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    /**
     * 详情
     *
     * @param id
     * @return
     */
    public UserProfile byId(long id) {
        DbUserProfile dbUserProfile = userProfileRepository.byId(id);
        if (Objects.isNull(dbUserProfile)) {
            return null;
        }
        return new UserProfile(dbUserProfile);
    }

    public long bindMobile(String deviceCode, String mobile) {
        return userProfileRepository.bindMobile(deviceCode, mobile);
    }

    public List<UserProfile> listUserProfiles(String deviceCode) {
        List<DbUserProfile> dbUserProfiles = userProfileRepository.listUserProfileArray(deviceCode);
        return dbUserProfiles.stream()
                .map(dbProfile -> new UserProfile(dbProfile)).collect(Collectors.toList());
    }

    /**
     * 是否有资料
     *
     * @param deviceCode
     * @return
     */
    public boolean hasAnyProfile(String deviceCode) {
        return userProfileRepository.getProfileCounts(deviceCode) > 0;
    }

}
