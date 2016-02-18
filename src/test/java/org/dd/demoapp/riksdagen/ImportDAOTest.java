package org.dd.demoapp.riksdagen;

import org.dd.demoapp.H2JDBIRule;
import org.dd.demoapp.question.Question;
import org.dd.demoapp.question.QuestionDAO;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ImportDAOTest {

    @Rule
    public H2JDBIRule db = new H2JDBIRule();

    private ImportDAO importDAO;
    private QuestionDAO questionDAO;

    @Before
    public void setUp() throws Exception {
        DBI dbi = db.getDbi();
        importDAO = dbi.onDemand(ImportDAO.class);
        questionDAO = dbi.onDemand(QuestionDAO.class);
        questionDAO.initDb();
    }

    @Test
    public void testBatchInsertQuestions_mergeInto() throws Exception {

        List<QuestionImportItem> questionImportItems = Arrays.asList(
            createQuestionImportItem("MPQ2E", "Ska Sverige ha ID-kontroller?", "2015-11-20"),
            createQuestionImportItem("MPQ2F", "Ska Sverige få ha avtal med Diktaturer?", "2016-03-20"),
            createQuestionImportItem("MPQ2G", "Ska Sverige utöka försvarsbudgeten med x antal kr?", "2016-03-23")
        );

        importDAO.batchInsertQuestions(questionImportItems);
        List<Question> questions = questionDAO.questions(Instant.EPOCH);

        assertThat(questions, Matchers.hasSize(3));

    }

    @Test
    public void testBatchInsertQuestions_mergeInto_replaces() throws Exception {

        List<QuestionImportItem> questionImportItems = Arrays.asList(
            createQuestionImportItem("MPQ2E", "Ska Sverige ha ID-kontroller?", "2015-11-20"),
            createQuestionImportItem("MPQ22", "Ska Sverige utöka försvarsbudgeten med x antal kr?", "2016-03-23"),
            createQuestionImportItem("MPQ2E", "Ska Sverige ha ID-kontroller?", "2015-11-21")
        );

        importDAO.batchInsertQuestions(questionImportItems);
        List<Question> questions = questionDAO.questions(Instant.EPOCH);

        assertThat(questions, Matchers.hasSize(2));
        assertThat(questions.get(0).getCloseTime(), equalTo(Instant.parse("2015-11-22T00:00:00.00Z")));
    }

    private QuestionImportItem createQuestionImportItem(String riksdagsId, String title, String closeTime) {
        return QuestionImportItem.newFromImportData(riksdagsId, title, "0", Optional.of(closeTime), Optional.empty(), Optional.empty());
    }
}