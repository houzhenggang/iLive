package izuanqian.api;

import io.swagger.annotations.ApiOperation;
import izuanqian.SubjectGeoService;
import izuanqian.TokenService;
import izuanqian.response.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static izuanqian.ApiHeader.HK_LATITUDE;
import static izuanqian.ApiHeader.HK_LONGITUDE;
import static izuanqian.ApiHeader.HK_TOKEN;

@RestController
@RequestMapping("/geo")
@io.swagger.annotations.Api(tags = "geo", description = "定位相关")
public class SubjectGeoApi {

    @Autowired private TokenService tokenService;
    @Autowired private SubjectGeoService subjectGeoService;

    @PostMapping
    @ApiOperation(value = "定位", response = Api.Ok.class)
    public Api locate(
            @RequestHeader(HK_TOKEN) String token,
            @RequestHeader(HK_LONGITUDE) double lng,
            @RequestHeader(HK_LATITUDE) double lat) {
        String deviceCode = tokenService.get(token);
        subjectGeoService.locate(deviceCode, lng, lat);
        return new Api.Ok();
    }
}
