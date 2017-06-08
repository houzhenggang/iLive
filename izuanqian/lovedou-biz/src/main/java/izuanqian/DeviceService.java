package izuanqian;

import izuanqian.device.DbDeviceInformation;
import izuanqian.device.DeviceRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sanlion on 2017/6/5.
 */
@Service
public class DeviceService {

    @Autowired private DeviceRepository deviceRepository;

    /**
     * 通过设备号查询当前设备信息
     *
     * @param code
     * @return
     */
    public Device byCode(String code) {
        DbDeviceInformation dbDeviceInformation = deviceRepository.get(code);
        return new Device(dbDeviceInformation);
    }

    /**
     * 保存设备信息
     *
     * @param deviceType
     * @param deviceCode
     * @param pushDeviceCode
     */
    public void save(DeviceType deviceType, String deviceCode, String pushDeviceCode) {
        deviceRepository.save(deviceType, deviceCode, pushDeviceCode);
    }

    /**
     * 更新设备的前台/后台状态
     *
     * @param deviceCode
     * @param isBack
     */
    public void updateAppBackState(String deviceCode, boolean isBack) {
        deviceRepository.updateBackState(deviceCode, isBack);
    }


    @Data
    public static class Device {

        private DeviceType deviceType;
        private String deviceCode;
        private String pushDeviceCode;
        private boolean back;

        public Device(DbDeviceInformation device) {

            this.deviceType = device.getDeviceType();
            this.deviceCode = device.getDeviceCode();
            this.pushDeviceCode = device.getPushDeviceCode();
            this.back = device.isBack();
        }
    }
}
