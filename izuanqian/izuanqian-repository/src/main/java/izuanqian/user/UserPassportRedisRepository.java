package izuanqian.user;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author sanlion do
 */
@Component
public class UserPassportRedisRepository {


    /**
     * generate code
     *
     * @return
     */
    public long nextCode() {
//        String yyyyMMddHHmmss = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
//        long initValue = Long.parseLong(yyyyMMddHHmmss + "00000");
//        return ignite.atomicSequence(yyyyMMddHHmmss, initValue, true).getAndIncrement();
        return new Random().nextLong();
    }
}
