package izuanqian.user.dbo;

import lombok.Data;

@Data
public class DboMobile {

    private long id;
    private String mobile;

    public DboMobile(long id, String mobile) {
        this.id = id;
        this.mobile = mobile;
    }
}
