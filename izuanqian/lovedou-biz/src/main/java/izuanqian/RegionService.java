package izuanqian;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author sanlion do
 */
@Service
public class RegionService {

    @Autowired private GeoDecoderClient geoDecoderClient;
    @Autowired private ThirdKeyManager thirdKeyManager;

    /**
     * getOnlineDevice recommend address
     *
     * @param lng
     * @param lat
     * @return
     */
    public String getLocationName(double lng, double lat) {
        GeoVo.Body value = geoDecoderClient.decode(lat + "," + lng)
                .value(17042101, "load location information failed.");
        return value.getFormatted_addresses().getRecommend();
    }
}
