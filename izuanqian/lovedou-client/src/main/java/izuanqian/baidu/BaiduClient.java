package izuanqian.baidu;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "client-Baidu", url = "http://api.map.baidu.com/")
public interface BaiduClient {

    /**
     * @param location lat,lng
     * @return
     */
    @RequestMapping(value = "/geocoder/v2/?output=json&pois=1", method = RequestMethod.GET)
    String demo(
            @RequestParam("location") String location,
            @RequestParam("ak") String baidukey
    );

    /**
     * 圆形区域索搜
     *
     * @param keyword
     * @param location
     * @param radius
     * @param baidukey
     * @param filter   industry_type=[hotel（宾馆）；cater（餐饮）；life（生活娱乐）]
     * @return
     */
    @RequestMapping(value = "/place/v2/search?output=json&scope=2", method = RequestMethod.GET)
    String search(
            @RequestParam("query") String keyword,
            @RequestParam("location") String location,
            @RequestParam("radius") int radius,
            @RequestParam("ak") String baidukey,
            @RequestParam("filter") String filter
    );

}
