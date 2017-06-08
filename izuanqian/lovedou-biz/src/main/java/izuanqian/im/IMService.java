/*
    © sanlion.do 
 */
package izuanqian.im;

import com.taobao.api.ApiException;
import izuanqian.BizException;
import izuanqian.openim.OpenIMUserClient;
import izuanqian.openim.o.IMUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sanlion
 */
@Service
public class IMService {

    @Autowired private OpenIMUserClient openIMUserClient;

    public void join() {
        try {
            openIMUserClient.initAdmin();
        } catch (ApiException ex) {
            throw new BizException(ex.getMessage());
        }
    }

    public void join(String token) {
        try {
            openIMUserClient.add(new IMUser(token, "SANLION.DO", "http://img.izuanqian.com/avatar.jpg", "M"));
        } catch (ApiException ex) {
            throw new BizException(ex.getMessage());
        }
    }
}
