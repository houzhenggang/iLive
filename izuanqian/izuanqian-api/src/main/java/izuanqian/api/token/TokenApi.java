package izuanqian.api.token;

import com.taobao.api.ApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import izuanqian.DeviceType;
import izuanqian.TokenService;
import izuanqian.api.token.o.vo.TokenVo;
import izuanqian.openmessage.OpenNotify4AndroidClient;
import izuanqian.openmessage.OpenNotify4iOSClient;
import izuanqian.response.ApiResponse;
import izuanqian.response.Ok;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

import static izuanqian.ApiHeader.*;

/**
 * Created by PC on 2017/4/5.
 */
@RestController
@RequestMapping("/api/token")
@Api(tags = "token", description = "apply token, and get token info")
public class TokenApi {

    @Autowired private TokenService tokenService;
    @Autowired private OpenNotify4AndroidClient openNotify4AndroidClient;
    @Autowired private OpenNotify4iOSClient openNotify4iOSClient;

    @PostMapping
    @ApiOperation(value = "apply token", response = String.class)
    public ApiResponse applyToken(
            @RequestHeader(value = HK_DEVICE_TYPE, defaultValue = "Android") DeviceType deviceType,
            @RequestHeader(HK_DEVICE_CODE) String deviceCode,
            @RequestHeader(HK_PUSH_DEVICE_CODE) String pushDeviceCode) throws ApiException {
        String token = tokenService.generateToken(deviceType, deviceCode);
//        openNotify4AndroidClient.notice("哈哈", "看到就证明我成功了", Arrays.asList(pushDeviceCode));
//        openNotify4iOSClient.notice("ios看看行不行", Arrays.asList(pushDeviceCode));
        return new Ok("", token);
    }

    @GetMapping
    @ApiOperation(value = "get token info", response = TokenVo.class)
    public ApiResponse get(
            @RequestHeader(HK_TOKEN) String token) {
        return new Ok("", tokenService.get(token));
    }
}
