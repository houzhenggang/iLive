package izuanqian;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static izuanqian.Key.__;
import static java.util.concurrent.TimeUnit.DAYS;

/**
 * @author sanlion do
 */
@Service
public class TokenRepository {

    @Autowired @Qualifier("db0") private StringRedisTemplate template;

    public void save(String token, String deviceCode) {
        clear(deviceCode);
        template.opsForValue().set(__("token:{0}", token), deviceCode, 3, DAYS);
        template.opsForValue().set(__("device:{0}", deviceCode), token);
    }

    public Optional<String> get(String token) {
        String deviceCode = template.opsForValue().get(__("token:{0}", token));
        return Optional.of(deviceCode);
    }

    private void clear(String deviceCode) {
        String token = template.opsForValue().get(__("device:{0}", deviceCode));
        if (!Strings.isNullOrEmpty(token)) {
            template.delete(__("token:{0}", token));
            template.delete(__("device:{0}", deviceCode));
        }
    }

}
