package izuanqian.device;

import izuanqian.user.dbo.DbUserProfile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SubjectMapper {

    /**
     * 关联
     *
     * @param deviceId
     * @param userProfileId
     */
    void associate(
            @Param("deviceId") long deviceId,
            @Param("userProfileId") long userProfileId);

    boolean alreadyBound(
            @Param("deviceId") long deviceId,
            @Param("userProfileId") long userProfileId);

    List<DbUserProfile> listUserProfileByDevice(String deviceCode);

    void inactiveAll(String deviceCode);

    void active(
            @Param("deviceCode") String deviceCode,
            @Param("userProfileId") long userProfileId);

    DbUserProfile fetchActiveProfile(String deviceCode);
}
