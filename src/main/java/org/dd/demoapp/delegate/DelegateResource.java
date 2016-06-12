package org.dd.demoapp.delegate;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.dd.demoapp.question.Question;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Service
@Path("/delegate")
@Api("/delegate")
@Produces(MediaType.APPLICATION_JSON)
public class DelegateResource {

    private final DelegateDAO dao;

    @Inject
    public DelegateResource(DelegateDAO dao) {
        this.dao = dao;
    }

    @GET
    @ApiOperation(value = "lists all delegates", response = Question.class, responseContainer="List")
    public List<Delegate> getDelegates() {
        return dao.getAll();
    }

    @GET
    @ApiOperation("gets a single delegate by it's reference")
    @Path("/{delegateReference}")
    public Delegate getDelegate(@PathParam("delegateReference") String delegateReference) {
        return dao.get(delegateReference);
    }

}
