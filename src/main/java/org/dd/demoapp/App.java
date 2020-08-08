package org.dd.demoapp;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.java8.Java8Bundle;
import io.dropwizard.java8.jdbi.DBIFactory;
import io.dropwizard.jdbi.bundles.DBIExceptionsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.dd.demoapp.common.DateTimeService;
import org.dd.demoapp.config.AppConfig;
import org.dd.demoapp.config.ImportConfig;
import org.dd.demoapp.config.managedjob.HK2ManagedJobsBundle;
import org.dd.demoapp.delegate.DelegateDAO;
import org.dd.demoapp.question.QuestionDAO;
import org.dd.demoapp.riksdagen.ImportDAO;
import org.dd.demoapp.riksdagen.ImportQuestionsJob;
import org.dd.demoapp.riksdagen.betankande.Parser;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.servlet.ServletProperties;
import org.skife.jdbi.v2.DBI;

import javax.inject.Singleton;

public class App extends Application<AppConfig> {

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<AppConfig> bootstrap) {
        bootstrap.addBundle(new Java8Bundle());
        bootstrap.addBundle(new AssetsBundle("/app", "/", "index.html"));
        bootstrap.addBundle(new DBIExceptionsBundle());
        bootstrap.addBundle(new HK2ManagedJobsBundle("org.dd.demoapp.riksdagen"));
        bootstrap.addBundle(new SwaggerBundle<AppConfig>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(AppConfig configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });
    }

    @Override
    public void run(AppConfig configuration, Environment environment) throws Exception {

        DBIFactory factory = new DBIFactory();
        DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "h2");

//        ServiceLocator locator = ServiceLocatorUtilities.createAndPopulateServiceLocator();
        ServiceLocator locator = ServiceLocatorUtilities.bind(new AbstractBinder() {

            @Override
            protected void configure() {
                QuestionDAO questionDAO = jdbi.onDemand(QuestionDAO.class);
                questionDAO.initDb();

                DelegateDAO delegateDAO = jdbi.onDemand(DelegateDAO.class);
                delegateDAO.initDb();

                ImportDAO importDAO = jdbi.onDemand(ImportDAO.class);

                ImportConfig dataImport = configuration.getDataImport();

                bind(questionDAO).to(QuestionDAO.class);
                bind(delegateDAO).to(DelegateDAO.class);
                bind(importDAO).to(ImportDAO.class);
                bind(dataImport).to(ImportConfig.class);

                // fixme: figure out a way to scan these instead
                bind(DateTimeService.class).to(DateTimeService.class).in(Singleton.class);
                bind(Parser.class).to(Parser.class).in(Singleton.class);
                bind(ImportQuestionsJob.class).to(ImportQuestionsJob.class);

            }
        });

        environment.getApplicationContext().getAttributes().setAttribute(ServletProperties.SERVICE_LOCATOR, locator);
        environment.jersey().getResourceConfig().packages(true, "org.dd.demoapp");
        enableMillisecondsInJsonSerialization(environment);
        environment.jersey().getResourceConfig().logComponents();

    }

    private void enableMillisecondsInJsonSerialization(Environment environment) {
        // TODO consider implementing Instant conversion ourselfs since there is a bug: https://github.com/FasterXML/jackson-datatype-jsr310/issues/39
        ObjectMapper objectMapper = environment.getObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS);
        objectMapper.disable(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS);
    }

    @Override
    public String getName() {
        return "DD Demo App";
    }

}
