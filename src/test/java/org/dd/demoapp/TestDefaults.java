package org.dd.demoapp;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.dropwizard.java8.jersey.OptionalMessageBodyWriter;
import io.dropwizard.java8.jersey.OptionalParamFeature;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.hamcrest.FeatureMatcher;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;

public interface TestDefaults {

    default ResourceTestRule.Builder resourceBuilder() {
        return ResourceTestRule.builder()
                .setMapper(new ObjectMapper()
                        .registerModule(new JavaTimeModule())
                        .disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS)
                        .disable(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS)
                )
                .addProvider(OptionalMessageBodyWriter.class)
                .addProvider(OptionalParamFeature.class);
    }

    class OptionalIsPresentMatcher<V> extends FeatureMatcher<Optional<V>, Boolean> {

        public OptionalIsPresentMatcher() {
            super(is(true), "Optional.isPresent", "Optional.isPresent");
        }

        @Override
        protected Boolean featureValueOf(Optional<V> target) {
            return target.isPresent();
        }
    }
}
