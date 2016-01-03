package org.dd.demoapp.hello;

import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.Optional;

@Service
@Path("/hello")
public class HelloResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloResource.class);

    @GET
    public String hello(@QueryParam("name") Optional<String> nameOpt) {
        String name = nameOpt.orElse("Incognito");
        LOGGER.info("Hello to {}!", name);
        return "Hello " + name + "!";
    }

}
