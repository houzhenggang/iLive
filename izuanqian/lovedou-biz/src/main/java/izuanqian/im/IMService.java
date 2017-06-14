/*
    © sanlion.do 
 */
package izuanqian.im;

import com.taobao.api.ApiException;
import izuanqian.BizException;
import izuanqian.TokenService;
import izuanqian.openim.OpenIMTribeClient;
import izuanqian.openim.OpenIMUserClient;
import izuanqian.openim.o.IMUser;
import izuanqian.tribe.IMTribeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sanlion
 */
@Slf4j
@Service
public class IMService {

    @Autowired private OpenIMUserClient openIMUserClient;
    @Autowired private TokenService tokenService;
    @Autowired private IMTribeRepository imTribeRepository;
    @Autowired private OpenIMTribeClient openIMTribeClient;

    public void join() {
        try {
            openIMUserClient.initAdmin();
        } catch (ApiException ex) {
            throw new BizException(ex.getMessage());
        }
    }

    public void join(String token) {
        String deviceCode = tokenService.get(token);
        try {
            openIMUserClient.add(new IMUser(deviceCode, "SANLION.DO", "http://img.izuanqian.com/avatar.jpg", "M"));
        } catch (ApiException ex) {
            throw new BizException(ex.getMessage());
        }
    }


}
