package izuanqian.tribe;

import com.google.gson.Gson;
import izuanqian.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class IMTribeRepository {

    @Autowired
    @Qualifier("tokenRedisTemplate")
    private StringRedisTemplate template;

    public void save(long id, String name, String logo) {
        String key = Key.__("im:tribe:{0}", id);
        template.opsForValue().set(
                key,
                new Gson().toJson(new CachedTribe() {{
                    setId(id);
                    setName(name);
                    setLogo(logo);
                }}));
        // todo 一天过期，not 235959
        template.expire(key, 1, TimeUnit.DAYS);
    }

    public void join(long id, String deviceCode) {
        String key = Key.__("im:tribe:{0}", id);
        if (template.opsForSet().add(key, deviceCode).longValue() == 0) {
            return;
        }
        template.opsForSet().add(Key.__("im:member:{0}", deviceCode), String.valueOf(id));
    }

    public void quit(long id, String deviceCode) {
        // 从群组中移除用户
        template.opsForSet().remove(Key.__("im:tribe:{0}", id), deviceCode);
        // 从用户的组列表中移除该群组
        template.opsForSet().remove(Key.__("im:member:{0}", deviceCode), String.valueOf(id));
    }
}
