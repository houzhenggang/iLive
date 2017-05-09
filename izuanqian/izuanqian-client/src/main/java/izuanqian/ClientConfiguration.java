/*
    © sanlion.do 
 */
package izuanqian;

import feign.Client;
import feign.Logger;
import feign.codec.Decoder;
import feign.gson.GsonDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sanlion
 */
@Slf4j
@Configuration
@EnableFeignClients
public class ClientConfiguration {

//    @Bean
//    public Decoder decoder() {
//        return new GsonDecoder();
//    }

    @Bean
    public Client okHttpClient() {
        return new feign.okhttp.OkHttpClient();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
