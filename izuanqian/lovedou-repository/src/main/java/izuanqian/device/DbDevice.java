package izuanqian.device;

import lombok.Data;

@Data
public class DbDevice {

    private long id;
    private int type;
    private String code;
    private String pushCode;
    private int state;
}
