package izuanqian.message;

import lombok.Data;

@Data
public class IMMessage {

    private long id;
    private IMMessageType type;

    @Data
    public static class Text extends IMMessage{

        private String content;
    }

    @Data
    public static class Image extends IMMessage{

        private String url;
    }
}
