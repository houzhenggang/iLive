package izuanqian.device;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeviceMapper {

    /**
     * 插入设备信息
     *
     * @param type
     * @param code
     */
    void saveDevice(int type, String code);

    /**
     * 更新设备的前后台信息
     *
     * @param code
     * @param state
     */
    void updateDeviceState(String code, int state);

    /**
     * 设备详情
     * @param code
     * @return
     */
    DbDevice byCode(String code);
}
