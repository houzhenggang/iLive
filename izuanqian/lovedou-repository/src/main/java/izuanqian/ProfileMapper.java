package izuanqian;

import izuanqian.user.dbo.DbProfile;
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
    List<DbProfile> queryByDeviceCode(String deviceCode);

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
