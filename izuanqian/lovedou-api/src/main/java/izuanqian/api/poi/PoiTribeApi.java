package izuanqian.api.poi;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import izuanqian.MeiTuanWaiMaiPoiRepository;
import izuanqian.TokenService;
import izuanqian.im.IMTribeService;
import izuanqian.response.Api;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static izuanqian.ApiHeader.HK_LATITUDE;
import static izuanqian.ApiHeader.HK_LONGITUDE;
import static izuanqian.ApiHeader.HK_TOKEN;

/**
 * Created by sanlion on 2017/5/18.
 */
@RestController
@io.swagger.annotations.Api(tags = "tribe", description = "兴趣点部落")
@RequestMapping("/poi/tribe")
public class PoiTribeApi {

    @Autowired private IMTribeService imTribeService;
    @Autowired private TokenService tokenService;
    @Autowired private MeiTuanWaiMaiPoiRepository meiTuanWaiMaiPoiRepository;

    @GetMapping
    @ApiOperation(value = "部落列表", response = TribeArrayVo.class)
    public Api queryTribes() {
        String title = "苏州观前街矮子馅饼";
//        long a = imTribeService.create(title, "");
        TribeArrayVo tribeArrayVo = new TribeArrayVo();
        tribeArrayVo.getTribes().add(new TribeVo(2217303461l, title));

        return new Api.Ok("", tribeArrayVo);
    }

    @GetMapping("/byMt")
    @ApiOperation(value = "部落列表", response = TribeArrayVo.class)
    public Api queryTribesByMt(

            @RequestHeader(required = false, value = HK_LONGITUDE) double lng,
            @RequestHeader(required = false, value = HK_LATITUDE) double lat,
            @RequestParam String address
    ) throws IOException {
        String title = "苏州观前街矮子馅饼";
//        long a = imTribeService.create(title, "");
        TribeArrayVo tribeArrayVo = new TribeArrayVo();
        tribeArrayVo.getTribes().add(new TribeVo(2217303461l, title));
        meiTuanWaiMaiPoiRepository.query(lng, lat, address)
                .stream().forEach(poi ->
                tribeArrayVo.getTribes().add(new TribeVo(00L, poi.getTitle(), poi.getLogo()))
        );
        return new Api.Ok("", tribeArrayVo);
    }

    @PostMapping("/join/{id}")
    @ApiOperation(value = "加入部落", response = Api.Ok.class)
    public Api join(
            @RequestHeader(HK_TOKEN) String token,
            @PathVariable long id) {
        imTribeService.join(id, token);
        return new Api.Ok();
    }

    @Data
    public static class TribeVo {

        @ApiModelProperty("部落ID") private long id;
        @ApiModelProperty("部落logo") private String logo;
        @ApiModelProperty("部落名称") private String name;

        public TribeVo(long id, String name) {
            this.id = id;
            this.name = name;
        }

        public TribeVo(long id, String name, String logo) {
            this.id = id;
            this.name = name;
            this.logo = logo;
        }
    }

    @Data
    public static class TribeArrayVo {

        @ApiModelProperty private List<TribeVo> tribes = new ArrayList<>();
    }
}
