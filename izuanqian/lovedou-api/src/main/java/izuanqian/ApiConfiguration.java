package izuanqian;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author sanlion do
 */
@Getter
@Configuration
@PropertySource(value = "classpath:api.properties")
public class ApiConfiguration {

    @Value("${live.title}") private String defaultLiveTitle;
}
