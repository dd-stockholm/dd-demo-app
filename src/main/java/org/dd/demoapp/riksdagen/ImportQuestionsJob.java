package org.dd.demoapp.riksdagen;

import de.spinscale.dropwizard.jobs.Job;
import de.spinscale.dropwizard.jobs.annotations.On;
import de.spinscale.dropwizard.jobs.annotations.OnApplicationStart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

//@On("0 0 2 * * ?") fixme: enable when we get 'real' questions
@OnApplicationStart
public class ImportQuestionsJob extends Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportQuestionsJob.class);

    private final ImportDAO dao;

    @Inject
    public ImportQuestionsJob(ImportDAO dao) {
        this.dao = dao;
    }

    @Override
    public void doJob() {
        LOGGER.info("Importing questions from data.riksdagen.se");

        List<QuestionImportItem> questionImportItems = Arrays.asList(
            createQuestionImportItem("Ska Sverige ha ID-kontroller?", LocalDateTime.of(2015, 11, 20, 23, 59).toInstant(ZoneOffset.UTC)),
            createQuestionImportItem("Ska Sverige få ha avtal med Diktaturer?", LocalDateTime.of(2016, 3, 20, 23, 59).toInstant(ZoneOffset.UTC)),
            createQuestionImportItem("Ska Sverige utöka försvarsbudgeten med x antal kr?", LocalDateTime.of(2016, 3, 23, 23, 59).toInstant(ZoneOffset.UTC))
        );

        dao.batchInsertQuestions(questionImportItems);

        LOGGER.info("Import done, {} questions imported.", questionImportItems.size());
    }

    private QuestionImportItem createQuestionImportItem(String title, Instant closeTime) {
        return new QuestionImportItem(title, "", closeTime);
    }
}