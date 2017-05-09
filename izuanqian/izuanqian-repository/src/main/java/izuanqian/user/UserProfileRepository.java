package izuanqian.user;

import izuanqian.user.dbo.DbUserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author sanlion do
 */
@Component
public class UserProfileRepository {

    @Autowired @Qualifier("db1") private StringRedisTemplate template;

    public void save(DbUserProfile dbUserProfile){
        template.opsForHash().put("profile", dbUserProfile.getId(), dbUserProfile);
    }
}
