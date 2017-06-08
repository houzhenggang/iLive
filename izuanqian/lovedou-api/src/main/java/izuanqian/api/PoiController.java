package izuanqian.api;

import io.swagger.annotations.ApiOperation;
import izuanqian.GaoDeDiTuRepository;
import izuanqian.MeiTuanWaiMaiPoiRepository;
import izuanqian.PoiSearch;
import izuanqian.PoiSearchService;
import izuanqian.api.poi.PoiTribeApi;
import izuanqian.response.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static izuanqian.ApiHeader.*;

/**
 * @author sanlion do
 */
@RestController
@io.swagger.annotations.Api(tags = "poi", description = "兴趣点")
@RequestMapping("/poi")
public class PoiController {

    @Autowired private PoiSearchService poiSearchService;

    @GetMapping
    @ApiOperation(value = "/兴趣点", response = PoiSearch.class)
    public Api list(
            @RequestHeader(required = false, value = HK_TOKEN) String token,
            @RequestHeader(required = false, value = HK_CITY) String city,
            @RequestHeader(required = false, value = HK_LONGITUDE) double lng,
            @RequestHeader(required = false, value = HK_LATITUDE) double lat,
            @RequestParam String keyword,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "20") int pageSize) {
        List<PoiSearch> search = poiSearchService.search(city, lng, lat, keyword, page, pageSize);
        return new Api.Ok("", search);
    }

    @GetMapping("/byMt")
    @ApiOperation(value = "部落列表", response = PoiTribeApi.TribeArrayVo.class)
    public Api queryTribes(
            @RequestHeader(required = false, value = HK_LONGITUDE) double lng,
            @RequestHeader(required = false, value = HK_LATITUDE) double lat,
            @RequestParam String address) throws IOException {
        return new Api.Ok("",
                meiTuanWaiMaiPoiRepository.query(lng, lat, address));
    }

    @GetMapping("/byGd")
    @ApiOperation(value = "部落列表<=高德地图，搜索", response = PoiTribeApi.TribeArrayVo.class)
    public Api queryTribesByGd(
            @RequestHeader(required = false, value = HK_LONGITUDE) double lng,
            @RequestHeader(required = false, value = HK_LATITUDE) double lat,
            @RequestHeader(required = false, value = "city") String city,
            @RequestParam(required = false, defaultValue = "美食") String keyword) throws IOException {

        return new Api.Ok("",
                gaoDeDiTuRepository.search(city, lng, lat, keyword));
    }

    @Autowired private MeiTuanWaiMaiPoiRepository meiTuanWaiMaiPoiRepository;
    @Autowired private GaoDeDiTuRepository gaoDeDiTuRepository;
}
