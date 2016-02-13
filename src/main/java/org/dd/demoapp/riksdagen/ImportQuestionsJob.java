package org.dd.demoapp.riksdagen;

import de.spinscale.dropwizard.jobs.Job;
import de.spinscale.dropwizard.jobs.annotations.On;
import de.spinscale.dropwizard.jobs.annotations.OnApplicationStart;
import org.dd.demoapp.question.QuestionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@On("0 18 23 * * ?")
@OnApplicationStart
public class ImportQuestionsJob extends Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportQuestionsJob.class);

    private final QuestionDAO dao;

    @Inject
    public ImportQuestionsJob(QuestionDAO dao) {
        this.dao = dao;
    }

    @Override
    public void doJob() {
        LOGGER.info("Importing questions from data.riksdagen.se");
        LOGGER.info("db access with: {}", dao);
    }
}