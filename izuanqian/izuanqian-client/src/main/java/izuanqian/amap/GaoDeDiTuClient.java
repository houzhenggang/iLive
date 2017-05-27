package izuanqian.amap;

import lombok.Data;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanlion on 2017/5/27.
 */
@FeignClient(
        name = "client-GaoDeDiTu",
        url = "http://ditu.amap.com/service")
public interface GaoDeDiTuClient {

    /**
     * 从高德地图搜索
     *
     * @param city
     * @param geoObj lng|lat|lng|lat
     * @param keyword
     * @return
     */
    @RequestMapping(
            value = "/poiInfo?query_type=TQUERY&pagesize=50&pagenum=1&qii=true&cluster_state=5&need_utd=true&utd_sceneid=1000&addr_poi_merge=true&is_classify=true&zoom=17",
            method = RequestMethod.GET)
    Vo search(@RequestParam("city") String city,
              @RequestParam(value = "geoobj", defaultValue = "美食") String geoObj,
              @RequestParam("keywords") String keyword);

    @Data
    public static class Vo {

        private Body data;

        @Data
        public static class Body {

            private List<Poi> poi_list = new ArrayList<>();
        }

        @Data
        public static class Poi {

            private String id;
            private String name;
            private String address;
            private String longitude;
            private String latitude;
            private String tel;
            private List<Domain> domain_list = new ArrayList<>();
        }

        @Data
        public static class Domain {

            private String value;
        }
    }
}
