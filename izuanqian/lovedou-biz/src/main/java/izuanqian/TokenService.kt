package izuanqian

import com.google.common.base.Strings
import com.google.common.hash.Hashing
import izuanqian.im.IMService
import izuanqian.user.domain.Mobile
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.nio.charset.Charset
import java.util.*

/**
 * by sanlion do
 */
@Service
class TokenService {

    @Autowired private val tokenRepository: TokenRepository? = null
    @Autowired private val imService: IMService? = null
    @Autowired private val deviceService: DeviceService? = null

    /**
     * generate token
     *
     * @param deviceCode
     * @param deviceType
     */
    fun generateToken(deviceType: DeviceType, deviceCode: String): String {
        val utf8 = Charset.forName("UTF8")
        val token = Hashing.md5().newHasher()
                .putString(UUID.randomUUID().toString(), utf8)
                .putString(deviceType.name, utf8)
                .putString(deviceCode, utf8)
                .hash().toString().toUpperCase()
        tokenRepository!!.save(token, deviceCode)
        imService!!.join(token)
        return token
    }

    fun token(token: String): String {
        val deviceCode = tokenRepository!!.getDeviceCode(token)
        if (Strings.isNullOrEmpty(deviceCode)) {
            throw BizException(17060501, "登陆令牌加载失败")
        }
        return deviceCode
    }

    /**
     *  valid and get current mobile
     */
    fun validAndGet(token: String): Mobile {
        var deviceCode = token(token)
        val mobile: Mobile? = deviceService!!.getCurrentMobile(deviceCode)
        if (mobile == null) {
            throw BizException(17060801, "please bind your mobile first.");
        }
        return mobile
    }

    fun listMobiles(token: String): List<Mobile> {
        return Collections.emptyList();
    }

    fun hasAnyMobile(token: String): Boolean {
        var deviceCode = token(token)
        return deviceService!!.checkHasAnyMobile(deviceCode);
    }

    fun specifyCurrentMobile(token: String, mobileId: Long) {
        deviceService!!.specifyCurrentMobile(token(token), mobileId)
    }
}
