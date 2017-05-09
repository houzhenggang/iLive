package izuanqian.websocket;

import izuanqian.CustomSpringConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @author sanlion do
 */
@Slf4j
@Component
@ServerEndpoint(value = "/p2p/{from}/{to}", configurator = CustomSpringConfiguration.class)
public class P2pApi {

    @Inject private P2pSessions p2pSessions;
//    @Autowired private Cacher cacher;

    @OnOpen
    public void onOpen(Session session, @PathParam("from") String from, @PathParam("to") String to) throws IOException {
        log.info(session.getQueryString() + " on open");
        // cache session
        p2pSessions.cache(to, session);
//        session.getBasicRemote().sendText("欢迎");
    }

    @OnClose
    public void onClose(Session session, CloseReason reason, @PathParam("to") String to) {
        log.info(reason.getReasonPhrase());
        p2pSessions.clear(to);
    }

    @OnMessage
    public void onMessage(
            String message, Session session, @PathParam("from") String from, @PathParam("to") String to) throws IOException {
        p2pSessions.to(to).get().getBasicRemote().sendText(message);
        log.info("send message [from=" + from + ", to=" + to + ", message=" + message + "]");
        session.getBasicRemote().sendText(message);
    }
}
