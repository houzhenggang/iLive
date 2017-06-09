package izuanqian.user;

import izuanqian.user.dbo.DbUntreatedProfile;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UntreatedProfileMapper {

    /**
     * 通过设备id查询未经处理的资料信息
     *
     * @param deviceId
     * @return
     */
    DbUntreatedProfile byDeviceId(long deviceId);
}
