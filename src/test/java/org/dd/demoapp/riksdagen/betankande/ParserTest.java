package org.dd.demoapp.riksdagen.betankande;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.dd.demoapp.TestDefaults;
import org.dd.demoapp.riksdagen.QuestionImportItem;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

public class ParserTest implements TestDefaults {

    private URL source;

    @Before
    public void setUp() throws Exception {
        source = getClass().getResource("/betankande.json");
    }

    @Test
    public void testParseQuestions_allHaveRequiredProperties() throws Exception {

        List<QuestionImportItem> items = new Parser().parseQuestions(source);
        assertThat(items, everyItem(
            allOf(
                hasProperty("riksdagsId", notNullValue()),
                hasProperty("title", notNullValue()),
                hasProperty("closeTime", is(new OptionalIsPresentMatcher<>())),
                hasProperty("decided", is(false))
            )
        ));
    }

    @Test
    public void testParseQuestions_includeDocumentUrlWhenAvailable() throws Exception {

        Optional<QuestionImportItem> item = new Parser().parseQuestions(source)
            .stream()
            .filter(q -> q.getDocumentUrl().isPresent())
            .findAny();

        assertThat(item, new OptionalIsPresentMatcher<>());

    }

    @Test
    public void testParseQuestions_includeDescriptionWhenAvailable() throws Exception {

        Optional<QuestionImportItem> item = new Parser().parseQuestions(source)
            .stream()
            .filter(q -> q.getDescription().isPresent())
            .findAny();

        assertThat(item, new OptionalIsPresentMatcher<>());

    }

    @Test
    public void testParseQuestions_closeTimeIsBeslutsDagPlusOne() throws Exception {

        JsonNode rootNode = new ObjectMapper().readTree(source);
        String beslutsdag = rootNode.get("dokumentlista").get("dokument").get(0).get("beslutsdag").textValue();
        int beslutsDayOfMonth = LocalDate.parse(beslutsdag).getDayOfMonth();

        int closeDayInMonth = new Parser().parseQuestions(source)
            .get(0).getCloseTime().map(i -> LocalDateTime.ofInstant(i, ZoneId.of("UTC")).getDayOfMonth()).get();

        assertThat(closeDayInMonth, is(beslutsDayOfMonth +1));

    }

}