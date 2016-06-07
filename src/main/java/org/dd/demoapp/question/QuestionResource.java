package org.dd.demoapp.question;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.dd.demoapp.common.DateTimeService;
import org.jvnet.hk2.annotations.Service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Service
@Path("/question")
@Api("/question")
@Produces(MediaType.APPLICATION_JSON)
public class QuestionResource {

    private final QuestionDAO dao;
    private final DateTimeService dateTimeService;

    @Inject
    public QuestionResource(QuestionDAO dao, DateTimeService dateTimeService) {
        this.dao = dao;
        this.dateTimeService = dateTimeService;
    }

    @GET
    @ApiOperation(value = "lists active questions", response = Question.class, responseContainer="List")
    @Path("/active")
    public List<Question> activeQuestions() {
        return dao.questions(dateTimeService.now());
    }

    @GET
    @ApiOperation(value = "lists all questions", response = Question.class, responseContainer="List")
    @Path("/all")
    public List<Question> allQuestions() {
        return dao.questions(dateTimeService.epoch());
    }
}
