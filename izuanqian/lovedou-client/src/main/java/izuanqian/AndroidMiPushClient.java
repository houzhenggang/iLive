package izuanqian;

import com.google.gson.Gson;
import com.xiaomi.xmpush.server.Constants;
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

    /**
     * 群发
     *
     * @param pushDeviceCodes
     * @param title
     * @param description
     * @param payload
     * @throws IOException
     * @throws ParseException
     */
    public void push(
            List<String> pushDeviceCodes, String title, String description, Object payload)
            throws IOException, ParseException {
        Message message = new Message.Builder()
                .title(title)
                .description(description)
                .payload(new Gson().toJson(payload))
                .build();
        androidSender.send(message, pushDeviceCodes, 1);
    }

    public static void main(String[] args) {
        Constants.useOfficial();
        Sender sender = new Sender("IaXT5DFzHkIiJMs5Aczz+Q==");
        String payload = "payload";
        String title = "title";
        String description = "描述dlajdkdjladjas==========";
        Message message = new Message.Builder()
                .title(title)
                .description(description)
                .passThrough(0)
                .payload(payload)
//                .restrictedPackageName("com.izuanqian.ilive.201705211247")
                .extra("ticker", "This is a ticker text.")
                .build();
        try {
            sender.send(message, "IMLbOd7vpUT03XxTogIGAVYqrA2E8Bmw6IcHJEuM2o0=", 3);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
