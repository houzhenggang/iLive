package izuanqian.message;

import lombok.Getter;

public enum IMMessageType {

    txt(IMMessage.Text.class),
    img(IMMessage.Image.class);

    IMMessageType(Class messageClazz) {
        this.messageClazz = messageClazz;
    }

    @Getter private Class messageClazz;
}
