package com.izuanqian.iLive;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by sanlion on 2016/12/20.
 */

@Path("/hello")
public class HelloWorldRs {

    @Inject
    HelloWolldService helloWolldService;

    @GET
    @Path("/world")
    public Response sayHello() {
        return Response.ok().entity(helloWolldService.sayHello()).build();
    }
}
