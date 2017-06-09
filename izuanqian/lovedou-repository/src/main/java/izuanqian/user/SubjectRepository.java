package izuanqian.user;

import izuanqian.device.SubjectMapper;
import izuanqian.user.dbo.DbUserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectRepository {

    @Autowired private SubjectMapper subjectMapper;

    public void associate(long deviceId, long userProfileId) {
        subjectMapper.associate(deviceId, userProfileId);
    }

    /**
     * list user profile by device
     *
     * @param deviceCode
     * @return
     */
    public List<DbUserProfile> listUserProfileArray(String deviceCode) {
        return subjectMapper.listUserProfileByDevice(deviceCode);
    }

    /**
     * active profile
     *
     * @param deviceCode
     * @param userProfileId
     */
    public void active(String deviceCode, long userProfileId) {
        subjectMapper.inactiveAll(deviceCode);
        subjectMapper.active(deviceCode, userProfileId);
    }

    /**
     * inactive all profile
     *
     * @param deviceCode
     */
    public void inactive(String deviceCode) {
        subjectMapper.inactiveAll(deviceCode);
    }

    public DbUserProfile getActiveProfile(String deviceCode) {
        return subjectMapper.fetchActiveProfile(deviceCode);
    }
}
