package izuanqian.api.subject.o.vo;

import izuanqian.user.dbo.CachedProfile;
import izuanqian.user.domain.UserProfile;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SubjectProfileArrayVo {

    private List<SubjectProfileVo> profiles = new ArrayList<>();

    @Data
    public static class SubjectProfileVo {

        private String avatar;
        private String nick;
        private CachedProfile.Gender gender;
        private boolean active;

        public SubjectProfileVo(UserProfile userProfile) {

            this.active = userProfile.isActive();
            this.nick = userProfile.getNick();
            this.gender = userProfile.getGender();
        }
    }
}
