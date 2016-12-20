package com.izuanqian.iLive;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.jboss.weld.environment.se.Weld;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Created by sanlion on 2016/12/20.
 */
public class Application {

    public static void main(String[] args) {

        URI uri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        ResourceConfig config = new ResourceConfig(new MyResourceConfig());
        Weld weld = new Weld();
        weld.initialize();
        JdkHttpServerFactory.createHttpServer(uri, config);
    }

    public static final class MyResourceConfig extends ResourceConfig {
        public MyResourceConfig() {
            register(HelloWorldRs.class);
        }
    }
}
