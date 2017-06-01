package izuanqian;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by sanlion on 2017/5/31.
 */
public interface Barista {

    @Input SubscribableChannel orders();

    @Output MessageChannel hot();
    @Output MessageChannel cold();
}
