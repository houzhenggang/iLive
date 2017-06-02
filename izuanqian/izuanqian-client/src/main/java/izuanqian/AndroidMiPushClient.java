package izuanqian;

import com.xiaomi.xmpush.server.Message;
import com.xiaomi.xmpush.server.Sender;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by sanlion on 2017/6/2.
 */
@Service
public class AndroidMiPushClient {

    @Autowired @Qualifier("androidSender") private Sender androidSender;

    public void demo(List<String> pushDeviceCodes) throws IOException, ParseException {

        String payload = "payload";
        String title = "title";
        String description = "描述";
        Message message = new Message.Builder()
                .title(title)
                .description(description)
                .payload(payload)
                .build();
        androidSender.send(message, pushDeviceCodes, 1);
    }
}
