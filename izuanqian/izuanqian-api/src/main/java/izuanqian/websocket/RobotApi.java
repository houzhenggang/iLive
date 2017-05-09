package izuanqian.websocket;

import izuanqian.CustomSpringConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @author sanlion do
 */
@Slf4j
@Component
@ServerEndpoint(value = "/robot", configurator = CustomSpringConfiguration.class)
public class RobotApi {

    @OnOpen
    public void onOpen(Session session) throws IOException {

    }

    @OnClose
    public void onClose(Session session, CloseReason reason) {

    }

    @OnMessage
    public void onMessage( String message, Session session) throws IOException {

    }
}
