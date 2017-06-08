package izuanqian;

import izuanqian.user.dbo.DbProfile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProfileMapper {

    List<DbProfile> queryByDeviceCode(String deviceCode);

}
