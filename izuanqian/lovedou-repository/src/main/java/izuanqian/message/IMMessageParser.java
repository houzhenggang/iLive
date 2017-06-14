package izuanqian.message;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class IMMessageParser {

    /**
     * parser to message object
     *
     * @param message
     * @return
     */
    public IMMessage parser(String message) {
        if (Objects.isNull(message)) {
            return null;
        }
        return new Gson().fromJson(message, IMMessage.class);
    }
}
