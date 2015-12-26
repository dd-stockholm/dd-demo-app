package org.dd.demoapp.hello;

import org.dd.demoapp.App;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Service
@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class HelloResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    @GET
    public String hello() {
        LOGGER.info("Got request for hello!");
        return "Hello!";
    }

}
