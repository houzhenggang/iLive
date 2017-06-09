package izuanqian;

import izuanqian.device.DbDeviceInformation;
import izuanqian.device.DeviceRepository;
import izuanqian.user.UserProfileService;
import izuanqian.user.domain.Mobile;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by sanlion on 2017/6/5.
 */
@Service
public class DeviceService {

    @Autowired private DeviceRepository deviceRepository;
    @Autowired private TokenService tokenService;
    @Autowired private UserProfileService userPassportService;

    /**
     * 通过设备号查询当前设备信息
     *
     * @param code
     * @return
     */
    public Device byCode(String code) {
        DbDeviceInformation dbDeviceInformation = deviceRepository.getOnlineDevice(code);
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

    public void updateAppState2Background(String token) {
        String deviceCode = tokenService.get(token);
        deviceRepository.updateBackState(deviceCode, DbDeviceInformation.DeviceState.Background);
    }

    public void updateAppState2Foreground(String token) {
        String deviceCode = tokenService.get(token);
        deviceRepository.updateBackState(deviceCode, DbDeviceInformation.DeviceState.Foreground);
    }

    public Mobile getCurrentMobile(String deviceCode) {
        Long currentMobileId = deviceRepository.getCurrentMobileId(deviceCode);
        if (Objects.isNull(currentMobileId)) {
            return null;
        }
        List<Mobile> mobiles = userPassportService.listMobiles(deviceCode);
        if (mobiles.isEmpty()) {
            return null;
        }
        return mobiles.stream()
                .filter(mobile -> currentMobileId.longValue() == mobile.getId())
                .findAny().orElseGet(null);
    }

    public void bindMobile(String token, String mobile) {
        String deviceCode = tokenService.get(token);
        long mobileId = userPassportService.bindMobile(deviceCode, mobile);
        // 并且指定为当前设备的默认号码
        specifyCurrentMobile(token, mobileId);
    }

    public void specifyCurrentMobile(String deviceCode, long mobileId) {
        deviceRepository.bindCurrentMobile(deviceCode, mobileId);
    }

    public boolean checkHasAnyProfile(String deviceCode) {
        return userPassportService.hasAnyProfile(deviceCode);
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
