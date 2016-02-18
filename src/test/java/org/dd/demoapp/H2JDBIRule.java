package org.dd.demoapp;

import com.codahale.metrics.MetricRegistry;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.java8.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.junit.rules.ExternalResource;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

public class H2JDBIRule extends ExternalResource {

    private DBI dbi;

    private Handle handle;

    public DBI getDbi() {
        return dbi;
    }

    @Override
    protected void before() throws Throwable {
        Environment environment = new Environment("test-env", Jackson.newObjectMapper(), null, new MetricRegistry(), null);
        dbi = new DBIFactory().build(environment, getDataSourceFactory(), "test");
        handle = dbi.open();
    }

    @Override
    protected void after() {
        try {
            handle.execute("DROP ALL OBJECTS");
        } catch (Exception e) {
            throw new RuntimeException("failed", e);
        }

        handle.close();
    }

    private DataSourceFactory getDataSourceFactory() {
        DataSourceFactory dataSourceFactory = new DataSourceFactory();
        dataSourceFactory.setDriverClass("org.h2.Driver");
        dataSourceFactory.setUrl("jdbc:h2:mem:test");
        dataSourceFactory.setUser("sa");
        dataSourceFactory.setPassword("");
        return dataSourceFactory;
    }
}
