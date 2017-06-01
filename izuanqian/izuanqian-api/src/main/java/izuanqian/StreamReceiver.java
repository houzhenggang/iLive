package izuanqian;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import java.util.Objects;

/**
 * Created by sanlion on 2017/5/31.
 */
@Slf4j
@EnableBinding(Sink.class)
public class StreamReceiver {

    @StreamListener(Sink.INPUT)
    public void messageSink(Object payload){
        log.info(payload.toString());
    }
}
