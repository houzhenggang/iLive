package izuanqian;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author sanlion do
 */
@FeignClient(name = "client-GeoDecoder", url = "${api.client.qq.geocoder}")
public interface GeoDecoderClient {

    @RequestMapping(method = RequestMethod.GET)
    GeoVo decode(@RequestParam("location") String location);
}
