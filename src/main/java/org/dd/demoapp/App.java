package org.dd.demoapp;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.java8.Java8Bundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.dd.demoapp.config.AppConfig;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class App extends Application<AppConfig> {

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<AppConfig> bootstrap) {
        bootstrap.addBundle(new Java8Bundle());
        bootstrap.addBundle(new AssetsBundle("/app", "/", "index.html"));
    }

    @Override
    public void run(AppConfig configuration, Environment environment) throws Exception {
        registerResources(environment);
    }

    private void registerResources(Environment environment) {
        environment.jersey().register(new AbstractBinder() {
            @Override
            protected void configure() {

            }
        });
        environment.jersey().getResourceConfig().packages(true, "org.dd.demoapp");
    }

    @Override
    public String getName() {
        return "DD Demo App";
    }
}
