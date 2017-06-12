package izuanqian.baidu;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "client-Baidu", url = "http://api.map.baidu.com/geocoder/v2")
public interface BaiduClient {

    /**
     * @param location lat,lng
     * @return
     */
    @RequestMapping(value = "?output=json&pois=1", method = RequestMethod.GET)
    String demo(
            @RequestParam("location") String location,
            @RequestParam("ak") String baidukey
    );

}
