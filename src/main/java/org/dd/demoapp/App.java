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
import org.dd.demoapp.common.DateTimeService;
import org.dd.demoapp.config.AppConfig;
import org.dd.demoapp.delegate.DelegateDAO;
import org.dd.demoapp.question.QuestionDAO;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.skife.jdbi.v2.DBI;

public class App extends Application<AppConfig> {

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<AppConfig> bootstrap) {
        bootstrap.addBundle(new Java8Bundle());
        bootstrap.addBundle(new AssetsBundle("/app", "/", "index.html"));
        bootstrap.addBundle(new DBIExceptionsBundle());
    }

    @Override
    public void run(AppConfig configuration, Environment environment) throws Exception {

        DBIFactory factory = new DBIFactory();
        DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "h2");

        environment.jersey().register(new AbstractBinder() {
            @Override
            protected void configure() {
                QuestionDAO questionDAO = jdbi.onDemand(QuestionDAO.class);
                questionDAO.initDb();

                DelegateDAO delegateDAO = jdbi.onDemand(DelegateDAO.class);
                delegateDAO.initDb();

                bind(questionDAO).to(QuestionDAO.class);
                bind(delegateDAO).to(DelegateDAO.class);
                bind(DateTimeService.class).to(DateTimeService.class);
            }
        });
        environment.jersey().getResourceConfig().packages(true, "org.dd.demoapp");
        enableMillisecondsInJsonSerialization(environment);
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
