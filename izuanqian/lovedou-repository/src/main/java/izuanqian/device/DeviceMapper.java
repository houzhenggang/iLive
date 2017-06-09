package izuanqian.device;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DeviceMapper {

    /**
     * 插入设备信息
     *
     * @param type
     * @param code
     */
    void saveDevice(@Param("type") int type, @Param("code") String code);

    /**
     * 更新设备的前后台信息
     *
     * @param code
     * @param state
     */
    void updateDeviceState(@Param("code") String code, @Param("state") int state);

    /**
     * 设备详情
     *
     * @param code
     * @return
     */
    DbDevice byCode(String code);

    /**
     * 是否含有指定code的设备记录
     *
     * @param code
     * @return
     */
    boolean hasAny(String code);
}
