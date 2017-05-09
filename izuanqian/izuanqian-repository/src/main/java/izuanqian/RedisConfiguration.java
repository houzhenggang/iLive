package izuanqian;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author sanlion do
 */
@Configuration
public class RedisConfiguration {

    @Bean
    @Qualifier("db0")
    public StringRedisTemplate db0(
            @Value("${redis.0.host}") String host) {
        return new StringRedisTemplate(
                connectionFactory(0, host));
    }

    @Bean
    @Qualifier("db1")
    public StringRedisTemplate db1(
            @Value("${redis.0.host}") String host) {
        return new StringRedisTemplate(
                connectionFactory(1, host));
    }

    private RedisConnectionFactory connectionFactory(int index, String host) {
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setDatabase(index);
        connectionFactory.setHostName(host);
        connectionFactory.afterPropertiesSet();
        return connectionFactory;
    }
}
