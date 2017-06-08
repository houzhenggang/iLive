package izuanqian;

import izuanqian.user.dbo.DbProfile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProfileMapper {

    /**
     * 通过device code 查询profile列表
     *
     * @param deviceCode
     * @return
     */
    List<DbProfile> queryByDeviceCode(String deviceCode);

    /**
     * 初始化插入profile
     *
     * @param id
     * @param code
     * @param mobile
     * @param deviceCode
     */
    void save(String id, long code, String mobile, String deviceCode);

    /**
     * 资料计数
     *
     * @param deviceCode
     * @return
     */
    long countUserProfile(String deviceCode);
}
