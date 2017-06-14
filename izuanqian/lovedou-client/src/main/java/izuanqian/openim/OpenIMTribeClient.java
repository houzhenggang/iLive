/*
    © sanlion.do 
 */
package izuanqian.openim;

import com.taobao.api.ApiException;
import com.taobao.api.domain.OpenImUser;
import com.taobao.api.request.OpenimTribeCreateRequest;
import com.taobao.api.request.OpenimTribeDismissRequest;
import com.taobao.api.request.OpenimTribeJoinRequest;
import com.taobao.api.request.OpenimTribeQuitRequest;
import com.taobao.api.response.OpenimTribeCreateResponse;
import com.taobao.api.response.OpenimTribeDismissResponse;
import com.taobao.api.response.OpenimTribeJoinResponse;
import com.taobao.api.response.OpenimTribeQuitResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static izuanqian.openim.OpenIMUserClient.TribeAdmin;


/**
 * @author sanlion
 */
@Service
public class OpenIMTribeClient {

    @Autowired private OpenApi im;


    public long create(String logo, String name) throws ApiException {
        OpenimTribeCreateRequest request = new OpenimTribeCreateRequest();
        request.setUser(TribeAdmin);
        request.setTribeName(name);
        request.setNotice("notice");
        request.setTribeType(0l);
        OpenimTribeCreateResponse response = im.client().execute(request);
        return response.getTribeInfo().getTribeId();
    }

    public void dismiss(long id) throws ApiException {
        OpenimTribeDismissRequest request = new OpenimTribeDismissRequest();
        request.setUser(TribeAdmin);
        request.setTribeId(id);
        OpenimTribeDismissResponse response = im.client().execute(request);
    }

    public void join(String uid, long id) throws ApiException {
        OpenimTribeJoinRequest request = new OpenimTribeJoinRequest();
        request.setTribeId(id);
        request.setUser(user(uid));
        OpenimTribeJoinResponse execute = im.client().execute(request);
    }

    public void quit(String uid, long id) throws ApiException {
        OpenimTribeQuitRequest request = new OpenimTribeQuitRequest();
        request.setTribeId(id);
        request.setUser(user(uid));
        OpenimTribeQuitResponse execute = im.client().execute(request);
    }

    private OpenImUser user(String uid) {
        return new OpenImUser() {
            {
                setUid(uid);
                setAppKey(OpenApi.appKey);
                setTaobaoAccount(Boolean.FALSE);
            }
        };
    }

}
