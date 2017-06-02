package izuanqian;

import com.xiaomi.xmpush.server.Constants;
import com.xiaomi.xmpush.server.Sender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sanlion on 2017/6/2.
 */
@Slf4j
@Configuration
public class MiPushConfiguration {

    @Bean
    @Qualifier("androidSender")
    public Sender androidSender(){
        log.info("小米推送（Android）已准备好。");
        Constants.useOfficial();
        return new Sender("5721757891937");
    }
}
