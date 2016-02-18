package org.dd.demoapp.riksdagen;

import de.spinscale.dropwizard.jobs.Job;
import de.spinscale.dropwizard.jobs.annotations.OnApplicationStart;
import org.dd.demoapp.config.ImportConfig;
import org.dd.demoapp.riksdagen.betankande.Parser;
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
    private final ImportConfig config;
    private final Parser parser;

    @Inject
    public ImportQuestionsJob(ImportDAO dao, ImportConfig config, Parser parser) {
        this.dao = dao;
        this.config = config;
        this.parser = parser;
    }

    @Override
    public void doJob() {
        LOGGER.info("Importing questions from data.riksdagen.se");

        List<QuestionImportItem> questionImportItems = Arrays.asList(
            createQuestionImportItem("MPQ2E", "Ska Sverige ha ID-kontroller?", "2015-11-20"),
            createQuestionImportItem("MPQ2F", "Ska Sverige få ha avtal med Diktaturer?", "2016-03-20"),
            createQuestionImportItem("MPQ2G", "Ska Sverige utöka försvarsbudgeten med x antal kr?", "2016-03-23")
        );

        dao.batchInsertQuestions(questionImportItems);

        LOGGER.info("Import done, {} questions imported.", questionImportItems.size());
    }

    private QuestionImportItem createQuestionImportItem(String riksdagsId, String title, String closeTime) {
        return QuestionImportItem.newFromImportData(riksdagsId, title, "0", Optional.of(closeTime), Optional.empty(), Optional.empty());
    }
}