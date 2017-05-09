package izuanqian.apidoc;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import izuanqian.apidoc.version.Debugging;
import izuanqian.apidoc.version.System;
import izuanqian.apidoc.version.V100;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Created by sanlion on 17-3-3.
 */
@Configuration
@EnableSwagger2
@Controller
public class OnlineHelpDocConfiguration {

    @GetMapping("/doc")
    public String doc() {
        return "redirect:swagger-ui.html";
    }

    @GetMapping("/")
    String index() {
        return "redirect:/doc";
    }

    @Value("${api.doc.enable}")
    private boolean enable;
    @Value("${server.port}") private int port;

    @Bean
    public Docket system() {
        return apidoc(
                System.class,
                "system",
                "系统相关");
    }

    @Bean
    public Docket debugging() {
        return apidoc(
                Debugging.class,
                "debugging",
                "正在调试的接口");
    }

    @Bean
    public Docket v100() {
        return apidoc(
                V100.class,
                "1.0.0",
                "直播核心功能");
    }

    @Bean
    public Docket all() {
        return apidoc(
                ApiOperation.class,
                "all",
                "所有的可用Api都在这里能找到");
    }

    private Docket apidoc(Class<? extends Annotation> annotation, String version, String description) {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .groupName(version)
                .select().apis(RequestHandlerSelectors.withMethodAnnotation(annotation)).build()
                .apiInfo(new ApiInfo(
                        ":" + port,
                        description,
                        version,
                        "http://www.myquanwei.com/",
                        new Contact("sanlion.do", "https://github.com/sanlion/", "sanlion@vip.qq.com"),
                        "apache 2.0",
                        ""))
                .useDefaultResponseMessages(false);
    }
}
