package izuanqian.api;

import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import izuanqian.*;
import izuanqian.api.poi.PoiTribeApi;
import izuanqian.baidu.BaiduClient;
import izuanqian.baidu.vo.LocationVo;
import izuanqian.response.Api;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static izuanqian.ApiHeader.*;

/**
 * @author sanlion do
 */
@RestController
@io.swagger.annotations.Api(tags = "poi", description = "兴趣点")
@RequestMapping("/poi")
public class PoiController {

    @Autowired private PoiSearchService poiSearchService;
    @Autowired private BaiduPoiService baiduPoiService;

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
        List<GaoDeDiTuRepository.DboGaoDeDiTuPoi> search = gaoDeDiTuRepository.search(city, lng, lat, keyword);
        return new Api.Ok("",
                search.subList(0, Math.min(search.size(), 5)));
    }

    @Autowired private MeiTuanWaiMaiPoiRepository meiTuanWaiMaiPoiRepository;
    @Autowired private GaoDeDiTuRepository gaoDeDiTuRepository;

    @Autowired private BaiduClient baiduClient;

    @GetMapping("/baidu/test")
    @ApiOperation(value = "百度逆地址解析", response = String.class)
    public Api test(@RequestHeader(HK_LONGITUDE) double lng,
                    @RequestHeader(HK_LATITUDE) double lat) {
        String ak = "z5js42W2np1ws91jEuLnuQyytgIBdyyT";
        String location = new StringBuilder(String.valueOf(lat)).append(",").append(String.valueOf(lng)).toString();
        String demo = baiduClient.demo(location, ak);
        return new Api.Ok("", new Gson().fromJson(demo, LocationVo.class));
    }

    @GetMapping("/baidu/search")
    @ApiOperation(value = "10km范围内的餐饮", response = SearchPoiVo.class)
    public Api search(@RequestHeader(HK_LONGITUDE) double lng,
                      @RequestHeader(HK_LATITUDE) double lat,
                      @RequestParam String keyword) {
        String ak = "z5js42W2np1ws91jEuLnuQyytgIBdyyT";
        List<BaiduPoiService.Poi> search = baiduPoiService.search(lng, lat, keyword);
        List<SearchPoiVo> vo = search.stream().map(poi -> new SearchPoiVo() {{
            setName(poi.getName());
            setAddress(poi.getAddress());
        }}).collect(Collectors.toList());
        return new Api.Ok("", vo);
    }


    @Data
    public static class SearchPoiVo {

        private String name;
        private String address;
    }
}
