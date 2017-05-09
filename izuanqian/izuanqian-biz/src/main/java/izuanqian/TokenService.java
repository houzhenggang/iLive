package izuanqian;

import com.google.common.hash.Hashing;
import izuanqian.device.DbDeviceInformation;
import izuanqian.device.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * by sanlion do
 */
@Service
public class TokenService {

    @Autowired private TokenRepository tokenRepository;
    @Autowired private DeviceRepository deviceRepository;

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
        return token;
    }

    public Token get(String token) {
        DbDeviceInformation device;
        try {
            device = deviceRepository.get(
                    tokenRepository.get(token).get()).get();
            return new Token(token, device);
        } catch (Exception e) {
            throw new BizException(17040601, "please check your token.");
        }
    }
}
