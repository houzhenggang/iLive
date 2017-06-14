/*
    © sanlion.do 
 */
package izuanqian.openim.o;

import com.google.gson.Gson;
import lombok.Data;

/**
 *
 * @author sanlion
 */
@Data
public class IMUser {

    private String id;
    private String password;
    private String nick;
    private String avatar;
    private String gender;
    private String extra; // json扩展字段

    public IMUser(String deviceCode, String nick, String avatar, String gender) {

        this.id = deviceCode;
        this.password = "2A137A220F394038"; // md5(sanion.do)
        this.nick = nick;
        this.avatar = avatar;
        this.gender = gender;
        this.extra = new Gson().toJson(new Extra(deviceCode));
    }

    public String token() {
        return new Gson().fromJson(this.extra, Extra.class).getToken();
    }

    @Data
    public static class Extra {

        private String token;

        public Extra(String token) {
            this.token = token;
        }
    }
}
