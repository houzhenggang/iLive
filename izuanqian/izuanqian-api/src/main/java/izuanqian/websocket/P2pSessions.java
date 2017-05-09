package izuanqian.websocket;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.util.Map;
import java.util.Optional;

/**
 * @author sanlion do
 */
@Component
public class P2pSessions {

    private static final Map<String, Session> sessions = Maps.newConcurrentMap();

    /**
     * find session
     *
     * @param to
     * @return
     */
    public Optional<Session> to(String to) {
        return sessions.containsKey(to) ? Optional.of(sessions.get(to)) : Optional.empty();
    }

    public void cache(String to, Session session){
        sessions.put(to, session);
    }

    public void clear(String to){
        sessions.remove(to);
    }
}
