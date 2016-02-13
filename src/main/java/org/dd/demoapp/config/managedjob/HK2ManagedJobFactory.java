package org.dd.demoapp.config.managedjob;

import io.dropwizard.setup.Environment;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.jersey.servlet.ServletProperties;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;

class HK2ManagedJobFactory implements JobFactory {

    private final Environment environment;

    public HK2ManagedJobFactory(Environment environment) {
        this.environment = environment;
    }

    @Override
    public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = bundle.getJobDetail();
        Class<? extends Job> jobClass = jobDetail.getJobClass();
        ServiceLocator locator = (ServiceLocator) environment.getApplicationContext()
                .getAttributes()
                .getAttribute(ServletProperties.SERVICE_LOCATOR);

        return ServiceLocatorUtilities.getService(locator, jobClass.getName());
    }
}
