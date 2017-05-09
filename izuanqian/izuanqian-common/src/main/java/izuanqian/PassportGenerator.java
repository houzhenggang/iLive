package izuanqian;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * @author sanlion do
 */
@Component
public class PassportGenerator {

    public String next(long code){
        return Hashing.md5().newHasher()
                .putLong(code)
                .putString(UUID.randomUUID().toString(), Charset.forName("utf8"))
                .putLong(System.currentTimeMillis())
                .hash().toString().toUpperCase();
    }
}
