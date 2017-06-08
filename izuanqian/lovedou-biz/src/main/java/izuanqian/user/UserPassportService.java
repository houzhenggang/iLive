package izuanqian.user;

import izuanqian.PassportGenerator;
import izuanqian.user.dbo.DboMobile;
import izuanqian.user.domain.Mobile;
import izuanqian.user.domain.PassportCreateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        for (int i = 0; i < 10000; i++) {
            System.out.println(String.valueOf(userPassportRedisRepository.nextCode()));
        }
        // 生成随机ID
        String id = passportGenerator.next(code);
        return new PassportCreateResult(id, code);
    }

    public long bindMobile(String deviceCode, String mobile) {
        return userPassportRedisRepository.bindMobile(deviceCode, mobile);
    }

    /**
     * 当前用户绑定的号码集合
     *
     * @param deviceCode
     * @return
     */
    public List<Mobile> listMobiles(String deviceCode) {
        List<DboMobile> dboMobiles = userPassportRedisRepository.listMobileArray(deviceCode);
        if (dboMobiles.isEmpty()) {
            return Collections.emptyList();
        }
        return dboMobiles.stream()
                .map(dboMobile -> new Mobile(dboMobile))
                .collect(Collectors.toList());
    }

    public boolean hasAnyMobile(String deviceCode){
        return userPassportRedisRepository.hasAnyMobile(deviceCode);
    }
}
