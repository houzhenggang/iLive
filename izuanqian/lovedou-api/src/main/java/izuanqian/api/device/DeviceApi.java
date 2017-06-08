package izuanqian.api.device;

import io.swagger.annotations.ApiOperation;
import izuanqian.DeviceService;
import izuanqian.response.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static izuanqian.ApiHeader.HK_DEVICE_CODE;
import static izuanqian.ApiHeader.HK_TOKEN;

/**
 * @author sanlion do
 */
@Slf4j
@RestController
@RequestMapping("/device")
@io.swagger.annotations.Api(tags = "device", description = "设备信息")
public class DeviceApi {

    @Autowired
    private DeviceService deviceService;

    @PostMapping("/background")
    @ApiOperation(value = "app background", response = Api.Ok.class)
    public Api background(
            @RequestHeader(HK_TOKEN) String token) {
        deviceService.updateAppState2Background(token);
        return new Api.Ok();
    }

    @PostMapping("/foreground")
    @ApiOperation(value = "app foreground", response = Api.Ok.class)
    public Api foreground(
            @RequestHeader(HK_TOKEN) String token) {
        deviceService.updateAppState2Foreground(token);
        return new Api.Ok();
    }

    @GetMapping
    @ApiOperation(value = "load device information.", response = DeviceService.Device.class)
    public Api device(
            @RequestHeader(HK_DEVICE_CODE) String deviceCode) {
        return new Api.Ok("", deviceService.byCode(deviceCode));
    }
}
