package izuanqian;

import izuanqian.amap.AmapPoiClient;
import izuanqian.amap.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sanlion do
 */
@Service
public class PoiSearchService {

    @Autowired private PoiClient poiClient;
    @Autowired private AmapPoiClient amapPoiClient;
    @Value("${api.client.amap.key}") private String key;

    public List<PoiSearch> search(String city, double lng, double lat, String keyword, int page, int pageSize) {
        SearchVo search
                = amapPoiClient.search(key, lng + "," + lat, keyword, city, pageSize, page, "all");
        if (search.getStatus() != 1) {
            return Collections.emptyList();
        }
        return search.getPois().stream().map(vo -> new PoiSearch(vo)).collect(Collectors.toList());
    }

}
