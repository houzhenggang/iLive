package izuanqian.amap;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author sanlion do
 */
@FeignClient(name = "client-amapPoi", url = "${api.client.amap.search}")
public interface AmapPoiClient {

    @RequestMapping(method = RequestMethod.GET)
    SearchVo search(
            @RequestParam("key") String key,
            //规则： 经度和纬度用","分割，经度在前，纬度在后，经纬度小数点后不得超过6位
            @RequestParam("location") String location,
            // 多个关键字用“|”分割
            @RequestParam("keywords") String keywords,
            @RequestParam("city") String city,
            @RequestParam("offset") int pageSize,
            @RequestParam("page") int page,
            // 此项默认返回基本地址信息；取值为all返回地址信息、附近POI、道路以及道路交叉口信息。
            @RequestParam("extensions") String extensions);
}
