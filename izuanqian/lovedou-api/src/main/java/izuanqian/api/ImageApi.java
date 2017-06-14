package izuanqian.api;

import io.swagger.annotations.ApiOperation;
import izuanqian.BizException;
import izuanqian.FileService;
import izuanqian.response.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author sanlion do
 */
@Slf4j
@Controller
@RequestMapping("/image")
@io.swagger.annotations.Api(tags = "upload", description = "file upload")
public class ImageApi {

    @Autowired
    private FileService fileService;

    @PostMapping
    @ApiOperation(value = "upload image, one by one", response = Api.Ok.class)
    public Api uploadFile(HttpServletRequest request) throws IOException {
//        return new Api.Ok("", fileService.save(file).toString());
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (!commonsMultipartResolver.isMultipart(request)) {
            throw new BizException("---");
        }
        List<String> urls = new ArrayList<>();
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        Iterator<String> fileNames = multiRequest.getFileNames();
        fileNames.forEachRemaining(name -> {
            MultipartFile file = multiRequest.getFile(name);
            try {
                URI save = fileService.save(file);
                urls.add(save.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return new Api.Ok("", urls.get(0));
    }

}
