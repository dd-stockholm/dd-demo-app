package org.dd.demoapp.config.managedjob;

import de.spinscale.dropwizard.jobs.JobManager;
import io.dropwizard.setup.Environment;
import org.quartz.impl.StdSchedulerFactory;

class HK2ManagedJobManager extends JobManager {
    private final Environment environment;

    public HK2ManagedJobManager(String scanURL, Environment environment) {
        super(scanURL);
        this.environment = environment;
    }

    @Override
    public void start() throws Exception {
        this.scheduler = StdSchedulerFactory.getDefaultScheduler();
        this.scheduler.setJobFactory(new HK2ManagedJobFactory(environment));
        this.scheduler.start();
        this.scheduleAllJobs();
    }

}
