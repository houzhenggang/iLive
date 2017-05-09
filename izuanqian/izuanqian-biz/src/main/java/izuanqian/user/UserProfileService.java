package izuanqian.user;

import izuanqian.DeviceType;
import izuanqian.user.dbo.DbUserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sanlion do
 */
@Service
public class UserProfileService {

    @Autowired private UserProfileRepository userProfileRepository;

    public void create(String id, long code, String deviceCode, DeviceType deviceType) {
        userProfileRepository.save(DbUserProfile.builder()
                .id(id).code(code).deviceCode(deviceCode).deviceType(deviceType)
                .build());
    }
}
