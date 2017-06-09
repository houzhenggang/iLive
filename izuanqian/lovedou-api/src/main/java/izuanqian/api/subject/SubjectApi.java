package izuanqian.api.subject;

import io.swagger.annotations.ApiOperation;
import izuanqian.BizException;
import izuanqian.DeviceService;
import izuanqian.TokenService;
import izuanqian.api.subject.o.vo.SubjectProfileArrayVo;
import izuanqian.api.token.o.vo.MobileArrayVo;
import izuanqian.api.token.o.vo.MobileId;
import izuanqian.response.Api;
import izuanqian.user.SubjectService;
import izuanqian.user.domain.UserProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static izuanqian.ApiHeader.HK_TOKEN;

/**
 * Created by PC on 2017/4/5.
 */
@Slf4j
@RestController
@RequestMapping("/api/subject")
@io.swagger.annotations.Api(tags = "subject")
public class SubjectApi {

    @Autowired private TokenService tokenService;
    @Autowired private DeviceService deviceService;
    @Autowired private SubjectService subjectService;

    @GetMapping
    @ApiOperation(value = "list available profile array.", response = SubjectProfileArrayVo.class)
    public Api listProfileArray(
            @RequestHeader(HK_TOKEN) String token) {
        String deviceCode = tokenService.get(token);
        SubjectProfileArrayVo subjectProfileArrayVo = new SubjectProfileArrayVo();
        List<UserProfile> userProfiles = subjectService.listProfile(deviceCode);
        if (!userProfiles.isEmpty()) {
            userProfiles.stream().forEach(
                    userProfile ->
                            subjectProfileArrayVo.getProfiles().add(
                                    new SubjectProfileArrayVo.SubjectProfileVo(userProfile))
            );
        }
        return new Api.Ok("", subjectProfileArrayVo);
    }

    @PostMapping("/{userProfileId}/active")
    @ApiOperation("active profile")
    public Api bindMobile(
            @RequestHeader(HK_TOKEN) String token,
            @PathVariable long userProfileId) {
        String deviceCode = tokenService.get(token);
        subjectService.active(deviceCode, userProfileId);
        return new Api.Ok();
    }

    @PostMapping("/{userProfileId}/inactive")
    @ApiOperation("inactive profile")
    public Api bindMobile(
            @RequestHeader(HK_TOKEN) String token) {
        String deviceCode = tokenService.get(token);
        subjectService.inactive(deviceCode);
        return new Api.Ok();
    }

    @GetMapping
    @ApiOperation(value = "get active profile", response = SubjectProfileArrayVo.SubjectProfileVo.class)
    public Api getActiveProfile(
            @RequestHeader(HK_TOKEN) String token
    ) {
        String deviceCode = tokenService.get(token);
        UserProfile activeProfile = subjectService.getActiveProfile(deviceCode);
        if (Objects.isNull(activeProfile)) {
            throw new BizException(17060902, "no any profile in active.");
        }
        return new Api.Ok("", new SubjectProfileArrayVo.SubjectProfileVo(activeProfile));
    }
}
