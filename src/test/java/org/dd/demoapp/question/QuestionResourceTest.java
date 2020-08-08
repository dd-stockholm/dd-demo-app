package org.dd.demoapp.question;

import io.dropwizard.testing.junit.ResourceTestRule;
import org.dd.demoapp.TestDefaults;
import org.dd.demoapp.common.DateTimeService;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.core.GenericType;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuestionResourceTest implements TestDefaults {

    private QuestionDAO dao = mock(QuestionDAO.class);
    private DateTimeService timeService = mock(DateTimeService.class);
    private QuestionResource resource = new QuestionResource(dao, timeService);

    @Rule
    public ResourceTestRule resources = resourceBuilder().addResource(resource).build();

    @Test
    public void testActiveQuestions() throws Exception {
        Instant now = LocalDateTime.of(2016, 1, 1, 1, 1, 1).toInstant(ZoneOffset.UTC);
        Question question1 = new QuestionBuilder().id("abc").build();
        List<Question> questions = Collections.singletonList(question1);

        when(timeService.now()).thenReturn(now);
        when(dao.questions(now)).thenReturn(questions);

        List<Question> response = resources.client().target("/question/active").request().get(new GenericType<List<Question>>() {});

        assertEquals(questions, response);
    }

    @Test
    public void testAllQuestions() throws Exception {
        Instant now = LocalDateTime.of(1970, 1, 1, 1, 1).toInstant(ZoneOffset.UTC);
        Instant closeTime = LocalDateTime.of(2016, 2, 11, 23, 59, 1, 500_000_000).toInstant(ZoneOffset.UTC);

        Question question1 = new QuestionBuilder().question("Weirdest question ever!").build();
        Question question2 = new QuestionBuilder().id("q2").riksdagsId("MKPLQ").closeTime(closeTime).build();
        List<Question> questions = Arrays.asList(question1, question2);

        when(timeService.epoch()).thenReturn(now);
        when(dao.questions(now)).thenReturn(questions);

        List<Question> response = resources.client().target("/question/all").request().get(new GenericType<List<Question>>() {});

        assertEquals(questions, response);
    }

    private static class QuestionBuilder {
        private Question question = Question.newInstance("123", "HAPKP", "A question one might ask!", Instant.EPOCH);

        private QuestionBuilder id(String id) {
            question = Question.newInstance(id, question.getRiksdagsId(), question.getQuestion(), question.getCloseTime());
            return this;
        }

        private QuestionBuilder riksdagsId(String riksdagsId) {
            question = Question.newInstance(question.getId(), riksdagsId, question.getQuestion(), question.getCloseTime());
            return this;
        }

        private QuestionBuilder question(String value) {
            question = Question.newInstance(question.getId(), question.getRiksdagsId(), value, question.getCloseTime());
            return this;
        }

        private QuestionBuilder closeTime(Instant closeTime) {
            question = Question.newInstance(question.getId(), question.getRiksdagsId(), question.getQuestion(), closeTime);
            return this;
        }

        private Question build() {
            return Question.newInstance(question.getId(), question.getRiksdagsId(), question.getQuestion(), question.getCloseTime());
        }
    }
}