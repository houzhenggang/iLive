package izuanqian.api.token;

import com.taobao.api.ApiException;
import io.swagger.annotations.ApiOperation;
import izuanqian.AndroidMiPushClient;
import izuanqian.DeviceType;
import izuanqian.TokenService;
import izuanqian.response.Api;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;

import static izuanqian.ApiHeader.*;

/**
 * Created by PC on 2017/4/5.
 */
@Slf4j
@RestController
@RequestMapping("/api/token")
@io.swagger.annotations.Api(tags = "token", description = "令牌")
public class TokenApi {

    @Autowired private TokenService tokenService;
    @Autowired private AndroidMiPushClient androidMiPushClient;

    @PostMapping
    @ApiOperation(value = "申请令牌", response = String.class)
    public Api applyToken(
            @RequestHeader(value = HK_DEVICE_TYPE, defaultValue = "Android") DeviceType deviceType,
            @RequestHeader(HK_DEVICE_CODE) String deviceCode,
            @RequestHeader(HK_PUSH_DEVICE_CODE) String pushDeviceCode) throws ApiException, IOException, ParseException {
        String token = tokenService.generateToken(deviceType, deviceCode);
        log.error(pushDeviceCode);
        androidMiPushClient.push(
                Arrays.asList(pushDeviceCode), "欢迎", "登陆令牌申请成功", token);
        return new Api.Ok("", token);
    }
}
