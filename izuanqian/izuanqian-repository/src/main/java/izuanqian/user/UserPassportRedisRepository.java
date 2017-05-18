package izuanqian.user;

import org.apache.ignite.Ignite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author sanlion do
 */
@Component
public class UserPassportRedisRepository {

    @Autowired private Ignite ignite;

    /**
     * generate code
     *
     * @return
     */
    public long nextCode() {
        String yyyyMMddHHmmss = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        long initValue = Long.parseLong(yyyyMMddHHmmss + "00000");
        return ignite.atomicSequence(yyyyMMddHHmmss, initValue, true).getAndIncrement();
    }
}
