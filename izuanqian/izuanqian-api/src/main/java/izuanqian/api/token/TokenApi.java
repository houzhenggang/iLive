package izuanqian.api.token;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import izuanqian.DeviceType;
import izuanqian.TokenService;
import izuanqian.api.token.o.vo.TokenVo;
import izuanqian.response.ApiResponse;
import izuanqian.response.Ok;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static izuanqian.ApiHeader.*;

/**
 * Created by PC on 2017/4/5.
 */
@RestController
@RequestMapping("/api/token")
@Api(tags = "token", description = "apply token, and get token info")
public class TokenApi {

    @Autowired private TokenService tokenService;

    @PostMapping
    @ApiOperation(value = "apply token", response = String.class)
    public ApiResponse applyToken(
            @RequestHeader(value = HK_DEVICE_TYPE, defaultValue = "Android") DeviceType deviceType,
            @RequestHeader(HK_DEVICE_CODE) String deviceCode) {
        String token = tokenService.generateToken(deviceType, deviceCode);
        return new Ok("", token);
    }

    @GetMapping
    @ApiOperation(value = "get token info", response = TokenVo.class)
    public ApiResponse get(
            @RequestHeader(HK_TOKEN) String token) {
        return new Ok("", tokenService.get(token));
    }
}
