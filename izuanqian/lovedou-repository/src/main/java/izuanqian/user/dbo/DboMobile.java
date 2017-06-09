package izuanqian.user.dbo;

import lombok.Data;

@Data
public class DboMobile {

    private String id;
    private String mobile;

    public DboMobile(String id, String mobile) {
        this.id = id;
        this.mobile = mobile;
    }
}
