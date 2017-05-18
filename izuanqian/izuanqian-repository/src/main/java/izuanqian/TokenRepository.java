package izuanqian;

import com.google.common.base.Strings;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static izuanqian.Key.__;

/**
 * @author sanlion do
 */
@Service
public class TokenRepository {

    @Autowired private Ignite ignite;

    public void save(String token, String deviceCode) {
        clear(deviceCode);
        ignite.getOrCreateCache("token").put(__("token:{0}", token), deviceCode);
    }

    public Optional<String> get(String token) {
        IgniteCache<String, String> cache = ignite.getOrCreateCache("token");
        String key = __("token:{0}", token);
        String deviceCode = cache.get(key);
        return Optional.of(deviceCode);
    }

    private void clear(String deviceCode) {
        IgniteCache<String, String> cache = ignite.getOrCreateCache("token");
        String key = __("device:{0}", deviceCode);
        String token = cache.get(key);
        if (!Strings.isNullOrEmpty(token)) {
            cache.remove(__("token:{0}", token));
            cache.remove(__("device:{0}", deviceCode));
        }
    }

}
