package org.dd.demoapp.riksdagen;

import de.spinscale.dropwizard.jobs.Job;
import de.spinscale.dropwizard.jobs.annotations.OnApplicationStart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
            createQuestionImportItem("Ska Sverige ha ID-kontroller?", "2015-11-20"),
            createQuestionImportItem("Ska Sverige få ha avtal med Diktaturer?", "2016-03-20"),
            createQuestionImportItem("Ska Sverige utöka försvarsbudgeten med x antal kr?", "2016-03-23")
        );

        dao.batchInsertQuestions(questionImportItems);

        LOGGER.info("Import done, {} questions imported.", questionImportItems.size());
    }

    private QuestionImportItem createQuestionImportItem(String title, String closeTime) {
        return QuestionImportItem.newFromImportData("", title, "0", Optional.empty(), Optional.of(closeTime), Optional.empty());
    }
}