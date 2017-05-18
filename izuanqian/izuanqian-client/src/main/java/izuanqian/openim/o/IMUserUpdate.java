/*
    © sanlion.do 
 */
package izuanqian.openim.o;

import lombok.Data;

/**
 *
 * @author sanlion
 */
@Data
public class IMUserUpdate {

    private String token;
    private String nick;
    private String avatar;
    private String gender;

    public IMUserUpdate(String token, String nick, String avatar, String gender) {
        this.token = token;
        this.nick = nick;
        this.avatar = avatar;
        this.gender = gender;
    }
}
