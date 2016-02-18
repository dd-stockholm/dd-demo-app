package org.dd.demoapp.riksdagen;

import de.spinscale.dropwizard.jobs.Job;
import de.spinscale.dropwizard.jobs.annotations.OnApplicationStart;
import org.dd.demoapp.common.DateTimeService;
import org.dd.demoapp.config.ImportConfig;
import org.dd.demoapp.riksdagen.betankande.Parser;
import org.dd.demoapp.riksdagen.betankande.URLBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.net.URL;
import java.util.List;
import java.util.Optional;

//@On("0 0 2 * * ?") fixme: enable when we get 'real' questions
@OnApplicationStart
public class ImportQuestionsJob extends Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportQuestionsJob.class);

    private final ImportDAO dao;
    private final ImportConfig config;
    private final Parser parser;
    private final DateTimeService dateTimeService;

    @Inject
    public ImportQuestionsJob(ImportDAO dao, ImportConfig config, Parser parser, DateTimeService dateTimeService) {
        this.dao = dao;
        this.config = config;
        this.parser = parser;
        this.dateTimeService = dateTimeService;
    }

    @Override
    public void doJob() {
        LOGGER.info("Importing questions from data.riksdagen.se");

        Optional<URL> importUrl =
            new URLBuilder(config.getDokumentlistaUrl(), config.getPeriod()).asUrl(dateTimeService.today());

        if (!importUrl.map(this::importQuestions).isPresent()) LOGGER.error("Failed to import from data.riksdagen.se!");
    }

    private int importQuestions(URL source) {
        List<QuestionImportItem> questionImportItems = parser.parseQuestions(source);
        dao.batchInsertQuestions(questionImportItems);
        LOGGER.info("Import done, {} questions imported.", questionImportItems.size());
        return questionImportItems.size();
    }

}