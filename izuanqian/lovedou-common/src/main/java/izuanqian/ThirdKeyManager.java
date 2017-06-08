package izuanqian;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author sanlion do
 */
@Component
public class ThirdKeyManager {

    @Getter @Value("${api.client.qq.key}") private String qqKey;
}
