package izuanqian.user;

import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import izuanqian.BizException;
import izuanqian.Key;
import izuanqian.ProfileMapper;
import izuanqian.user.dbo.DbProfile;
import izuanqian.user.dbo.DboMobile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author sanlion do
 */
@Service
public class UserProfileRepository {

    @Autowired
    @Qualifier("tokenRedisTemplate")
    private StringRedisTemplate tokenRedisTemplate;
    @Autowired
    private ProfileMapper profileMapper;


    /**
     * generate code
     *
     * @return
     */
    public long nextCode() {
        String idIncKey = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmm"));
        long initValue = Long.parseLong(idIncKey + "00001");
        ValueOperations<String, String> operations = tokenRedisTemplate.opsForValue();
        return operations.increment(idIncKey, tokenRedisTemplate.hasKey(idIncKey) ? 1 : initValue);
    }

    /**
     * bind mobile
     *
     * @param deviceCode
     * @param mobile
     */
    public long bindMobile(String deviceCode, String mobile) {
        boolean hasMobileBind = hasMobileBind(deviceCode, mobile);
        if (hasMobileBind) {
            throw new BizException(17060901, "the mobile has bind already.");
        }
        long code = nextCode();
        String id
                = Hashing.md5().newHasher()
                .putString("profile", Charset.forName("utf8"))
                .putLong(code).hash().toString();
        profileMapper.save(id, code, mobile, deviceCode);
        return code;
    }

    @Deprecated
    public List<DboMobile> listMobileArray(String deviceCode) {
        String key = Key.__("device:{0}:mobile", deviceCode);
        Set<String> mobiles = tokenRedisTemplate.opsForSet().members(key);
        if (Objects.isNull(mobiles) || mobiles.isEmpty()) {
            return Collections.emptyList();
        }
        return mobiles.stream()
                .map(mobile -> new Gson().fromJson(mobile, DboMobile.class))
                .collect(Collectors.toList());
    }

    public List<DbProfile> listUserProfileArray(String deviceCode) {
        List<DbProfile> dbProfiles = profileMapper.queryByDeviceCode(deviceCode);
        return Objects.isNull(dbProfiles) || dbProfiles.isEmpty() ? Collections.emptyList() : dbProfiles;
    }

    /**
     * 个人资料计数
     *
     * @param deviceCode
     * @return
     */
    public long getProfileCounts(String deviceCode) {
        return profileMapper.countUserProfile(deviceCode);
    }

    /**
     * 校验号码是否被绑定
     *
     * @param deviceCode
     * @param mobile
     * @return
     */
    public boolean hasMobileBind(String deviceCode, String mobile) {
        List<DbProfile> dbProfiles = profileMapper.queryByDeviceCode(deviceCode);
        return
                Objects.nonNull(dbProfiles)
                        && !dbProfiles.isEmpty()
                        && dbProfiles.stream().anyMatch(dbProfile -> mobile.equals(dbProfile.getMobile()));
    }
}
