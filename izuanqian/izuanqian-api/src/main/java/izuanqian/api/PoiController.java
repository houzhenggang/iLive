package izuanqian.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import izuanqian.PoiSearch;
import izuanqian.PoiSearchService;
import izuanqian.response.ApiResponse;
import izuanqian.response.Ok;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static izuanqian.ApiHeader.*;

/**
 * @author sanlion do
 */
@RestController
@Api(tags = "poi", description = "兴趣点")
@RequestMapping("/poi")
public class PoiController {

    @Autowired private PoiSearchService poiSearchService;

    @GetMapping
    @ApiOperation(value = "/兴趣点", response = PoiSearch.class)
    public ApiResponse list(
            @RequestHeader(required = false, value = HK_TOKEN) String token,
            @RequestHeader(required = false, value = HK_CITY) String city,
            @RequestHeader(required = false, value = HK_LONGITUDE) double lng,
            @RequestHeader(required = false, value = HK_LATITUDE) double lat,
            @RequestParam String keyword,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "20") int pageSize) {
        List<PoiSearch> search = poiSearchService.search(city, lng, lat, keyword, page, pageSize);
        return new Ok("", search);
    }
}
