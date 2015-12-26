package org.dd.demoapp;

import io.dropwizard.java8.jersey.OptionalMessageBodyWriter;
import io.dropwizard.java8.jersey.OptionalParamFeature;
import io.dropwizard.testing.junit.ResourceTestRule;

public interface TestDefaults {

    default ResourceTestRule.Builder resourceBuilder() {
        return ResourceTestRule.builder()
                .addProvider(OptionalMessageBodyWriter.class)
                .addProvider(OptionalParamFeature.class);
    }
}
