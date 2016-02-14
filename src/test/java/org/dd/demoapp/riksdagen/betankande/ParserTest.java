package org.dd.demoapp.riksdagen.betankande;

import org.dd.demoapp.riksdagen.QuestionImportItem;
import org.hamcrest.FeatureMatcher;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

public class ParserTest {

    private Parser parser;

    @Before
    public void setUp() throws Exception {
        URL source = getClass().getResource("/betankande.json");
        parser = new Parser(source);
    }

    @Test
    public void testParseQuestions_allHaveRequiredProperties() throws Exception {

        List<QuestionImportItem> items = parser.parseQuestions();
        assertThat(items, everyItem(
            allOf(
                hasProperty("riksdagsId", notNullValue()),
                hasProperty("title", notNullValue()),
                hasProperty("closeTime", is(new OptionalIsPresentMatcher<>())),
                hasProperty("decided", is(false))
            )
        ));
    }

    private static class OptionalIsPresentMatcher<V> extends FeatureMatcher<Optional<V>, Boolean> {

        public OptionalIsPresentMatcher() {
            super(is(true), "isPresent", "OptionalIsPresentMatcher");
        }

        @Override
        protected Boolean featureValueOf(Optional<V> target) {
            return target.isPresent();
        }
    }
}