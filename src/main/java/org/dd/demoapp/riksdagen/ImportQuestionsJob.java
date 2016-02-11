package org.dd.demoapp.riksdagen;

import org.knowm.sundial.Job;
import org.knowm.sundial.annotations.CronTrigger;
import org.knowm.sundial.exceptions.JobInterruptException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CronTrigger(cron = "0 1 21 * * ?")
public class ImportQuestionsJob extends Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportQuestionsJob.class);

    @Override
    public void doRun() throws JobInterruptException {
        LOGGER.info("Importing questions from data.riksdagen.se");
    }
}