/*
    © sanlion.do 
 */
package izuanqian.im;

import com.taobao.api.ApiException;
import izuanqian.BizException;
import izuanqian.openim.OpenIMTribeClient;
import org.springframework.stereotype.Service;

import javax.ejb.EJB;

/**
 *
 * @author sanlion
 */
@Service
public class IMTribeService {

    @EJB private OpenIMTribeClient openIMTribeClient;

    public long create(String name, String logo) {
        try {
            return openIMTribeClient.create(logo, name);
        } catch (ApiException ex) {
            throw new BizException(ex.getMessage());
        }
    }

    public void dismiss(long tribe) {
        try {
            openIMTribeClient.dismiss(tribe);
        } catch (ApiException ex) {
            throw new BizException(ex.getMessage());
        }
    }

    public void join(long tribe, String token) {
        try {
            openIMTribeClient.join(token, tribe);
        } catch (ApiException ex) {
            throw new BizException(ex.getMessage());
        }
    }

    public void quit(long tribe, String token) {
        try {
            openIMTribeClient.quit(token, tribe);
        } catch (ApiException ex) {
            throw new BizException(ex.getMessage());
        }
    }
}
