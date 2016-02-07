package org.dd.demoapp.delegate;

import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Service
@Path("/delegate")
@Produces(MediaType.APPLICATION_JSON)
public class DelegateResource {

    private final DelegateDAO dao;

    @Inject
    public DelegateResource(DelegateDAO dao) {
        this.dao = dao;
    }

    @GET
    @Path("/all")
    public List<Delegate> getDelegates() {
        return dao.getAll();
    }

}
