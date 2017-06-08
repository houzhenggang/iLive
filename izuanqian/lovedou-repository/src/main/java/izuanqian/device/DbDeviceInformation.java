package izuanqian.device;

import izuanqian.DBType;
import izuanqian.DeviceType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author sanlion do
 */
@Data
@NoArgsConstructor
public class DbDeviceInformation implements Serializable {

    private DeviceType deviceType;
    private String deviceCode;
    private String pushDeviceCode;
    private boolean back; // 是否在后台运行
    private long mobile;
    private DeviceState state;

    public DbDeviceInformation(DeviceType deviceType, String deviceCode) {
        this.deviceType = deviceType;
        this.deviceCode = deviceCode;
        this.back = false;
    }

    public DbDeviceInformation(DbDevice dbDevice) {

        this.deviceType = DBType.type(DeviceType.class, dbDevice.getType());
        this.deviceCode = dbDevice.getCode();
        this.pushDeviceCode = dbDevice.getPushCode();
        this.state = DBType.type(DeviceState.class, dbDevice.getState());
    }

    public DbDeviceInformation(CachedOnlineDevice cachedOnlineDevice){

        this.deviceType = cachedOnlineDevice.getType();
        this.deviceCode = cachedOnlineDevice.getCode();
        this.pushDeviceCode = cachedOnlineDevice.getPushCode();
        this.state = cachedOnlineDevice.getState();
    }

    public enum DeviceState {

        Foreground(1), Background(2);
        @Getter private int code;

        DeviceState(int code) {
            this.code = code;
        }
    }
}
