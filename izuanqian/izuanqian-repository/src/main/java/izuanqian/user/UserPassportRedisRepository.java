package izuanqian.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author sanlion do
 */
@Component
public class UserPassportRedisRepository {

    @Autowired @Qualifier("db0") private StringRedisTemplate template;
    private static final String codeIncr = "USER:CODE:INCR";

    /**
     * generate code
     *
     * @return
     */
    public long nextCode() {
        return template.opsForValue().increment(codeIncr, 1);
    }


}
