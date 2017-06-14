/*
    © sanlion.do 
 */
package izuanqian.im;

import com.taobao.api.ApiException;
import izuanqian.BizException;
import izuanqian.TokenService;
import izuanqian.openim.OpenIMTribeClient;
import izuanqian.tribe.IMTribeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ejb.EJB;

/**
 * @author sanlion
 */
@Service
public class IMTribeService {

    @Autowired private TokenService tokenService;
    @Autowired private IMTribeRepository imTribeRepository;
    @Autowired private OpenIMTribeClient openIMTribeClient;

    /**
     * 初始化群组
     *
     * @param name
     * @param logo
     * @return
     */
    public long create(String name, String logo) {
        try {
            long id = openIMTribeClient.create(logo, name);
            imTribeRepository.save(id, name, logo);
            return id;
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
        String deviceCode = tokenService.get(token);
        try {
            openIMTribeClient.join(deviceCode, tribe);
        } catch (ApiException ex) {
            throw new BizException(ex.getMessage());
        }
        imTribeRepository.join(tribe, deviceCode);
    }

    /**
     * 退群
     *
     * @param token
     * @param tribeId
     */
    public void quit(String token, long tribeId) {
        String deviceCode = tokenService.get(token);
        try {
            openIMTribeClient.quit(deviceCode, tribeId);
        } catch (ApiException e) {
            throw new BizException(e.getMessage());
        }
        imTribeRepository.quit(tribeId, deviceCode);
    }
}
