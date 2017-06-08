package izuanqian.user;

import com.google.gson.Gson;
import izuanqian.Key;
import izuanqian.user.dbo.DboMobile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author sanlion do
 */
@Component
public class UserPassportRedisRepository {

    @Autowired
    @Qualifier("tokenRedisTemplate")
    private StringRedisTemplate tokenRedisTemplate;


    /**
     * generate code
     *
     * @return
     */
    public long nextCode() {
        String idIncKey = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        long initValue = Long.parseLong(idIncKey + "00000");
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
        long id = nextCode();
        tokenRedisTemplate.opsForSet().add(
                Key.__("device:{0}:mobile", deviceCode),
                new Gson().toJson(new DboMobile(id, mobile)));
        return id;
    }

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

    public boolean hasAnyMobile(String deviceCode) {
        String key = Key.__("device:{0}:mobile", deviceCode);
        return tokenRedisTemplate.opsForSet().size(key).longValue() > 0;
    }
}
