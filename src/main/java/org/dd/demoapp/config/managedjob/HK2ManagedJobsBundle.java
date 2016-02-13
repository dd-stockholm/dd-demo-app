package org.dd.demoapp.config.managedjob;

import de.spinscale.dropwizard.jobs.JobManager;
import de.spinscale.dropwizard.jobs.JobsBundle;
import io.dropwizard.setup.Environment;

public class HK2ManagedJobsBundle extends JobsBundle {

    public HK2ManagedJobsBundle(String scanUrl) {
        super(scanUrl);
    }

    @Override
    public void run(Environment environment) {
        JobManager jobManager = new HK2ManagedJobManager(this.scanURL, environment);
        environment.lifecycle().manage(jobManager);
    }

}
