package izuanqian;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class SubjectGeoRepository {

    @Autowired
    @Qualifier("poiRedisTemplate")
    private StringRedisTemplate template;

    /**
     * 记录设备的实时定位信息
     *
     * @param deviceCode
     */
    public void cacheLocate(String deviceCode, double lng, double lat) {
        template.opsForList().rightPush(Key.__("location:{0}", deviceCode),
                new StringBuilder(String.valueOf(lng)).append("/").append(String.valueOf(lat)).toString());
    }
}
