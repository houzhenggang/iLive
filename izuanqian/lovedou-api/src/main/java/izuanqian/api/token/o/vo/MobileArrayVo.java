package izuanqian.api.token.o.vo;

import io.swagger.annotations.ApiModelProperty;
import izuanqian.Mobiles;
import izuanqian.user.domain.Mobile;
import izuanqian.user.domain.UserProfile;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MobileArrayVo {

    private List<MobileVo> mobiles = new ArrayList<>();

    @Data
    public static class MobileVo {

        @ApiModelProperty("id")
        private long id;
        @ApiModelProperty("号码")
        private String mobile;

        public MobileVo(Mobile mobile) {
            this.id = mobile.getId();
            this.mobile = Mobiles.asterisk(mobile.getMobile());
        }

        public MobileVo(UserProfile userProfile) {
            this.id = userProfile.getId();
            this.mobile = Mobiles.asterisk(userProfile.getMobile());
        }
    }
}
