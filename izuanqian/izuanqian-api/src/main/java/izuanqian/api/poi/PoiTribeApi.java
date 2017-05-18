package izuanqian.api.poi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import izuanqian.TokenService;
import izuanqian.im.IMTribeService;
import izuanqian.response.ApiResponse;
import izuanqian.response.Ok;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static izuanqian.ApiHeader.HK_TOKEN;

/**
 * Created by sanlion on 2017/5/18.
 */
@RestController
@Api(tags = "tribe", description = "兴趣点部落")
@RequestMapping("/poi/tribe")
public class PoiTribeApi {

    @Autowired private IMTribeService imTribeService;
    @Autowired private TokenService tokenService;

    @GetMapping
    @ApiOperation(value = "部落列表", response = TribeArrayVo.class)
    public ApiResponse queryTribes() {
        String title = "苏州观前街矮子馅饼";
        long a = imTribeService.create(title, "");
        TribeArrayVo tribeArrayVo = new TribeArrayVo();
        tribeArrayVo.getTribes().add(new TribeVo(a, title));
        return new Ok("", tribeArrayVo);
    }

    @PostMapping("/join/{id}")
    @ApiOperation(value = "加入部落", response = Ok.class)
    public ApiResponse join(
            @RequestHeader(HK_TOKEN) String token,
            @PathVariable long id) {
        imTribeService.join(id, token);
        return new Ok();
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
    }

    @Data
    public static class TribeArrayVo {

        @ApiModelProperty private List<TribeVo> tribes = new ArrayList<>();
    }
}
