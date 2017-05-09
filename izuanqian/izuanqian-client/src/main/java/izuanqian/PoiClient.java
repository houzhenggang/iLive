package izuanqian;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author sanlion do
 */
@FeignClient(name = "client-poi", url = "${api.client.qq.search}")
public interface PoiClient {

    @RequestMapping(method = RequestMethod.GET)
    PoiArrayVo search(
            @RequestParam("orderby") String orderBy,
            @RequestParam("boundary") String boundary,
            @RequestParam("keyword") String keyword,
            @RequestParam("page_index") int page,
            @RequestParam("page_size") int pageSize);
}
