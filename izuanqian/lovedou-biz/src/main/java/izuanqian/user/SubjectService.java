package izuanqian.user;

import izuanqian.user.dbo.DbUserProfile;
import izuanqian.user.domain.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    @Autowired private SubjectRepository subjectRepository;

    /**
     * 资料列表
     *
     * @param deviceCode
     * @return
     */
    public List<UserProfile> listProfile(String deviceCode) {
        List<DbUserProfile> dbUserProfiles = subjectRepository.listUserProfileArray(deviceCode);
        return
                Objects.isNull(dbUserProfiles) || dbUserProfiles.isEmpty()
                        ? Collections.emptyList() :
                        dbUserProfiles.stream()
                                .map(dbUserProfile -> new UserProfile(dbUserProfile))
                                .collect(Collectors.toList());
    }


    public UserProfile getActiveProfile(String deviceCode) {
        DbUserProfile profile = subjectRepository.getActiveProfile(deviceCode);
        return Objects.nonNull(profile) ? new UserProfile(profile) : null;
    }


    public void active(String deviceCode, long userProfileId) {
        subjectRepository.active(deviceCode, userProfileId);
    }

    public void inactive(String deviceCode) {
        subjectRepository.inactive(deviceCode);
    }
}
