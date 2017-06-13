package izuanqian;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectGeoService {

    @Autowired private SubjectGeoRepository subjectGeoRepository;

    /**
     * 实时定位记录
     *
     * @param deviceCode
     * @param lng
     * @param lat
     */
    public void locate(String deviceCode, double lng, double lat) {
        subjectGeoRepository.cacheLocate(deviceCode, lng, lat);
    }
}
