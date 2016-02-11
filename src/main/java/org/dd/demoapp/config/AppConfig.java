package org.dd.demoapp.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import org.knowm.dropwizard.sundial.SundialConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class AppConfig extends Configuration {

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @Valid
    @NotNull
    public SundialConfiguration sundialConfiguration = new SundialConfiguration();

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory factory) {
        this.database = factory;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @JsonProperty("sundial")
    public SundialConfiguration getSundialConfiguration() {
        return sundialConfiguration;
    }
}