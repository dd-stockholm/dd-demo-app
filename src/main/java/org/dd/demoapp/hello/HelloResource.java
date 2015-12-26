package org.dd.demoapp.hello;

import org.dd.demoapp.App;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Service
@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class HelloResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    @GET
    public String hello(@QueryParam("name") Optional<String> nameOpt) {
        String name = nameOpt.orElse("Incognito");
        LOGGER.info("Hello to {}!", name);
        return "Hello " + name + "!";
    }

}
