package izuanqian;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sanlion on 2017/5/15.
 */
@Configuration
public class IgniteCacheConfiguration {

    @Bean
    public Ignite ignite() {
        IgniteConfiguration configuration = new IgniteConfiguration();
        return Ignition.start(configuration);
    }
}
