package izuanqian.api.token;

import io.swagger.annotations.ApiOperation;
import izuanqian.BizException;
import izuanqian.DeviceService;
import izuanqian.TokenService;
import izuanqian.api.token.o.vo.MobileRb;
import izuanqian.api.token.o.vo.MobileArrayVo;
import izuanqian.api.token.o.vo.MobileId;
import izuanqian.response.Api;
import izuanqian.user.UserProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static izuanqian.ApiHeader.HK_TOKEN;

/**
 * Created by PC on 2017/4/5.
 */
@Slf4j
@RestController
@RequestMapping("/api/token/mobile")
@io.swagger.annotations.Api(tags = "token", description = "令牌")
public class TokenMobileApi {

    @Autowired private TokenService tokenService;
    @Autowired private DeviceService deviceService;
    @Autowired private UserProfileService userProfileService;

    @GetMapping
    @ApiOperation(value = "号码列表", response = MobileArrayVo.class)
    public Api listMobileArray(
            @RequestHeader(HK_TOKEN) String token) {
        boolean hasAnyMobile = tokenService.hasAnyMobile(token);
        if (!hasAnyMobile) {
            throw new BizException(17060801, "please bind your mobile first.");
        }
        List<izuanqian.user.domain.Mobile> mobiles = tokenService.listMobiles(token);
        if (mobiles.isEmpty()) {
            throw new BizException(17060801, "please bind your mobile first.");
        }
        MobileArrayVo mobileArrayVo = new MobileArrayVo();
        mobiles.stream().forEach(
                mobile ->
                        mobileArrayVo.getMobiles().add(new MobileArrayVo.MobileVo(mobile)));
        return new Api.Ok("", mobileArrayVo);
    }

    @PostMapping
    @ApiOperation("绑定号码")
    public Api bindMobile(
            @RequestHeader(HK_TOKEN) String token,
            @RequestBody MobileRb mobile) {
        deviceService.bindMobile(token, mobile.getValue());
        return new Api.Ok();
    }

    @PostMapping("/current")
    @ApiOperation("specify current mobile")
    public Api updateCurrentMobile(
            @RequestHeader(HK_TOKEN) String token,
            @RequestBody MobileId mobile) {
        tokenService.specifyCurrentMobile(token, mobile.getValue());
        return new Api.Ok();
    }

}
