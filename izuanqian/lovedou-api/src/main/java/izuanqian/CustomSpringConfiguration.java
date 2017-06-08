package izuanqian;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

import static izuanqian.ApiHeader.HK_TOKEN;

/**
 * Created by PC on 2017/3/30.
 */
public class CustomSpringConfiguration extends SpringConfigurator implements ApplicationContextAware {

    private static volatile BeanFactory beanFactory;

    @Override
    public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
        return beanFactory.getBean(endpointClass);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CustomSpringConfiguration.beanFactory = applicationContext;
    }

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        super.modifyHandshake(sec, request, response);
//        String token = request.getHeaders().get(HK_TOKEN).stream().findAny().orElse("");
//        sec.getUserProperties().put(HK_TOKEN, token);
    }
}
