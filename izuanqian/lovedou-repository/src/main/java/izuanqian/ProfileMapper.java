package izuanqian;

import izuanqian.user.dbo.DbUserProfile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProfileMapper {

    /**
     * 通过device code 查询profile列表
     *
     * @param deviceCode
     * @return
     */
    List<DbUserProfile> queryByDeviceCode(String deviceCode);

    /**
     * get by id
     *
     * @param id
     * @return
     */
    DbUserProfile byId(long id);

    /**
     * 初始化插入profile
     *
     * @param id
     * @param mobile
     * @param deviceCode
     */
    void save(
            @Param("id") long id,
            @Param("mobile") String mobile,
            @Param("deviceCode") String deviceCode);

    /**
     * 资料计数
     *
     * @param deviceCode
     * @return
     */
    long countUserProfile(String deviceCode);
}
