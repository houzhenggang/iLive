package izuanqian.api.device;

import io.swagger.annotations.ApiOperation;
import izuanqian.DeviceService;
import izuanqian.api.device.o.AppBackStateUpdate;
import izuanqian.response.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static izuanqian.ApiHeader.HK_DEVICE_CODE;

/**
 * @author sanlion do
 */
@Slf4j
@RestController
@RequestMapping("/device")
@io.swagger.annotations.Api(tags = "device", description = "设备信息")
public class DeviceApi {

    @Autowired private DeviceService deviceService;

    @PostMapping("/back")
    @ApiOperation(value = "更新设备的前台/后台状态", response = Api.Ok.class)
    public Api back(
            @RequestHeader(HK_DEVICE_CODE) String deviceCode,
            @RequestBody AppBackStateUpdate body) {
        boolean isBack = false;
        switch (body.getState()) {
            case Background:
                isBack = true;
                break;
            case Foreground:
                isBack = false;
                break;
        }
        deviceService.updateAppBackState(deviceCode, isBack);
        return new Api.Ok();
    }

    @GetMapping
    @ApiOperation(value = "查看设备信息", response = DeviceService.Device.class)
    public Api device(
            @RequestHeader(HK_DEVICE_CODE) String deviceCode) {
        return new Api.Ok("", deviceService.byCode(deviceCode));
    }
}
