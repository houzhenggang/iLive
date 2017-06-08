package izuanqian;

import com.google.common.base.Strings;
import com.google.common.hash.Hashing;
import izuanqian.im.IMService;
import izuanqian.user.domain.Mobile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * by sanlion do
 */
@Service
public class TokenService {

    @Autowired private TokenRepository tokenRepository;
    @Autowired private IMService imService;
    @Autowired private DeviceService deviceService;

    /**
     * generate token by device
     *
     * @param deviceType
     * @param deviceCode
     * @return
     */
    public String generateToken(DeviceType deviceType, String deviceCode) {
        final Charset utf8 = Charset.forName("UTF8");
        String token
                = Hashing.md5().newHasher()
                .putString(UUID.randomUUID().toString(), utf8)
                .putString(deviceType.name(), utf8)
                .putString(deviceCode, utf8)
                .hash().toString().toUpperCase();
        tokenRepository.save(token, deviceCode);
        imService.join(token);
        return token;
    }

    public String get(String token) {
        String deviceCode = tokenRepository.getDeviceCode(token);
        if (Strings.isNullOrEmpty(deviceCode)) {
            throw new BizException(17060501, "登陆令牌加载失败");
        }
        return deviceCode;
    }

    public Mobile validAndGet(String token){
        String deviceCode = get(token);
        Mobile mobile = deviceService.getCurrentMobile(deviceCode);
        if (Objects.isNull(mobile)) {
            throw new BizException(17060801, "please bind your mobile first.");
        }
        return mobile;
    }

    public List<Mobile> listMobiles(String token){
        return Collections.emptyList();
    }

    public boolean hasAnyMobile(String token){
        String deviceCode = get(token);
        return deviceService.checkHasAnyMobile(deviceCode);
    }

    public void specifyCurrentMobile(String token, long mobileId){

        deviceService.specifyCurrentMobile(get(token), mobileId);
    }
}
