package izuanqian;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import static izuanqian.Key.__;

/**
 * @author sanlion do
 */
@Slf4j
@Service
public class TokenRepository {

    /*
    cache

    token队列只保存device信息的索引
     */

    @Autowired @Qualifier("tokenRedisTemplate") private StringRedisTemplate tokenRedisTemplate;

    public void save(String token, String deviceCode) {
        clear(deviceCode);
        String key = __("token:{0}:device", token);
        tokenRedisTemplate.opsForValue().set(key, deviceCode);
    }

    public String getDeviceCode(String token) {
        ValueOperations<String, String> operation = tokenRedisTemplate.opsForValue();
        String key = __("token:{0}:device", token);
        return operation.get(key);
    }


    private void clear(String deviceCode) {
        ValueOperations<String, String> operation = tokenRedisTemplate.opsForValue();
        String token = operation.get(__("token:device:{0}", deviceCode));
        if (!Strings.isNullOrEmpty(token)) {
            // 清除当前token，及device信息
            tokenRedisTemplate.delete(__("token:device:{0}", deviceCode));
            tokenRedisTemplate.delete(__("token:{0}:device", token));
        }
    }

}
