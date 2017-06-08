package izuanqian.api;

import io.swagger.annotations.ApiOperation;
import izuanqian.FileService;
import izuanqian.response.Api;
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
@io.swagger.annotations.Api(tags = "upload", description = "file upload")
public class UploadApi {

    @Autowired
    private FileService fileService;

    @PostMapping
    @ApiOperation(value = "upload image, one by one", response = Api.Ok.class)
    public Api uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return new Api.Ok("", fileService.save(file).toString());
    }

}
