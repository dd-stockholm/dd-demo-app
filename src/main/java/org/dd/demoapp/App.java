package org.dd.demoapp;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.dd.demoapp.config.AppConfig;
import org.dd.demoapp.hello.HelloResource;

import java.util.ArrayList;
import java.util.List;

public class App extends Application<AppConfig> {

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<AppConfig> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/angularjs/app", "/", "index.html"));
    }

    @Override
    public void run(AppConfig configuration, Environment environment) throws Exception {
        registerResources(environment);
    }

    private void registerResources(Environment environment) {
        for (Object resource : resources()) {
            environment.jersey().register(resource);
        }
    }

    @Override
    public String getName() {
        return "DD Demo App";
    }

    private List<Object> resources() {
        ArrayList<Object> resources = new ArrayList<>();

        resources.add(new HelloResource());

        return resources;
    }
}
