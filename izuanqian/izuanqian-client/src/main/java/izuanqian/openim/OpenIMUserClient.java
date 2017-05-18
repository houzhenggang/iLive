/*
    © sanlion.do 
 */
package izuanqian.openim;

import com.google.common.base.Strings;
import com.taobao.api.ApiException;
import com.taobao.api.domain.OpenImUser;
import com.taobao.api.domain.Userinfos;
import com.taobao.api.request.OpenimUsersAddRequest;
import com.taobao.api.request.OpenimUsersDeleteRequest;
import com.taobao.api.request.OpenimUsersUpdateRequest;
import izuanqian.openim.o.IMUser;
import izuanqian.openim.o.IMUserUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author sanlion
 */
@Service
public class OpenIMUserClient {

    @Autowired private OpenApi im;
    private static final String ADMINID = "EB184CC3E4469723F6AFDA6B176EBB49"; // md5(admin)
    public static final OpenImUser TribeAdmin = new OpenImUser() {
        {
            setUid(ADMINID);
            setAppKey(OpenApi.appKey);
            setTaobaoAccount(Boolean.FALSE);
        }
    };

    /**
     * 批量加入OPENIM的账号体系
     *
     * @param user
     * @throws ApiException
     */
    public void add(IMUser user) throws ApiException {

        OpenimUsersAddRequest request = new OpenimUsersAddRequest();
        request.setUserinfos(
                Arrays.asList(
                        new Userinfos() {
                            {
                                setNick(user.getNick());
                                setGender(user.getGender());
                                setIconUrl(user.getAvatar());
                                setPassword(user.getPassword());
                                setUserid(user.getId());
                                setExtra(user.getExtra());
                            }
                        }));
        im.client().execute(request);
    }

    /**
     * 更新im账号信息
     *
     * @param update
     * @throws ApiException
     */
    public void update(IMUserUpdate update) throws ApiException {
        Userinfos user = new Userinfos();
        user.setUserid(update.getToken());
        if (!Strings.isNullOrEmpty(update.getAvatar())) {
            user.setIconUrl(update.getAvatar());
        }
        if (!Strings.isNullOrEmpty(update.getGender())) {
            user.setGender(update.getGender());
        }
        if (!Strings.isNullOrEmpty(update.getNick())) {
            user.setNick(update.getNick());
        }
        OpenimUsersUpdateRequest request = new OpenimUsersUpdateRequest();
        request.setUserinfos(Arrays.asList(user));
        im.client().execute(request);
    }

    /**
     * 从im服务器删除账号
     *
     * @param token
     * @throws ApiException
     */
    public void remove(String token) throws ApiException {
        OpenimUsersDeleteRequest request = new OpenimUsersDeleteRequest();
        request.setUserids(token);
        im.client().execute(request);
    }

    public void initAdmin() throws ApiException {
        add(new IMUser(ADMINID, "izuanqian.admin", "", ""));
    }
}
