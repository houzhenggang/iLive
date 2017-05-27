package izuanqian.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import izuanqian.FileService;
import izuanqian.response.ApiResponse;
import izuanqian.response.Ok;
import izuanqian.user.UserPassportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author sanlion do
 */
@Slf4j
@RestController
@RequestMapping("/image")
@Api(tags = "upload", description = "file upload")
public class UploadApi {

    @Autowired private FileService fileService;

    @Autowired private UserPassportService userPassportService;
    @PostMapping
    @ApiOperation(value = "upload image, one by one", response = Ok.class)
    public ApiResponse uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        userPassportService.create();
        return new Ok("", fileService.save(file).toString());
    }

}