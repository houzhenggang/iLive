package izuanqian;

import lombok.Getter;

/**
 * @author sanlion do
 */
public enum DeviceType {

    Android(1), iOS(2);
    @Getter private int code;

    DeviceType(int code) {
        this.code = code;
    }
}
