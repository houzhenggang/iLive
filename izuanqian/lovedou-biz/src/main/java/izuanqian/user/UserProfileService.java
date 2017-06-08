package izuanqian.user;

import izuanqian.user.dbo.DbProfile;
import izuanqian.user.dbo.DboMobile;
import izuanqian.user.domain.Mobile;
import izuanqian.user.domain.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sanlion do
 */
@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;


    public long bindMobile(String deviceCode, String mobile) {
        return userProfileRepository.bindMobile(deviceCode, mobile);
    }

    /**
     * 当前用户绑定的号码集合
     *
     * @param deviceCode
     * @return
     * @deprecated
     */
    public List<Mobile> listMobiles(String deviceCode) {
        List<DboMobile> dboMobiles = userProfileRepository.listMobileArray(deviceCode);
        if (dboMobiles.isEmpty()) {
            return Collections.emptyList();
        }
        return dboMobiles.stream()
                .map(dboMobile -> new Mobile(dboMobile))
                .collect(Collectors.toList());
    }

    public List<UserProfile> listUserProfiles(String deviceCode) {
        List<DbProfile> dbProfiles = userProfileRepository.listUserProfileArray(deviceCode);
        return dbProfiles.stream()
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
