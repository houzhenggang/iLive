package izuanqian.user;

import izuanqian.PassportGenerator;
import izuanqian.user.domain.PassportCreateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sanlion do
 */
@Service
public class UserPassportService {

    @Autowired private UserPassportRedisRepository userPassportRedisRepository;
    @Autowired private PassportGenerator passportGenerator;

    /**
     * generate a unique passport
     *
     * @return
     */
    public PassportCreateResult create() {

        // 生成易记的code号
        long code = userPassportRedisRepository.nextCode();
        // 生成随机ID
        String id = passportGenerator.next(code);
        return new PassportCreateResult(id, code);
    }
}
