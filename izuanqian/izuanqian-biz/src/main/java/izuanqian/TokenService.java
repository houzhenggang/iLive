package izuanqian;

import com.google.common.base.Strings;
import com.google.common.hash.Hashing;
import izuanqian.device.DbDeviceInformation;
import izuanqian.device.DeviceRepository;
import izuanqian.im.IMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Objects;
import java.util.UUID;

/**
 * by sanlion do
 */
@Service
public class TokenService {

    @Autowired private TokenRepository tokenRepository;
    @Autowired private DeviceRepository deviceRepository;
    @Autowired private IMService imService;

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
        deviceRepository.save(deviceType, deviceCode);
        imService.join(token);
        return token;
    }

    public DbDeviceInformation get(String token) {
        String deviceCode = tokenRepository.getDeviceCode(token);
        if (Strings.isNullOrEmpty(deviceCode)) {
            throw new BizException(17060501, "登陆令牌加载失败");
        }
        DbDeviceInformation device = deviceRepository.get(deviceCode);
        if (Objects.isNull(device)) {
            throw new BizException(17060502, "在线设备信息加载失败");
        }
        return device;
    }
}
