package org.dd.demoapp.riksdagen;

import org.dd.demoapp.common.DateTimeService;
import org.dd.demoapp.config.ImportConfig;
import org.dd.demoapp.riksdagen.betankande.Parser;
import org.hamcrest.CustomTypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ImportQuestionsJobTest {

    @Mock
    private ImportDAO dao;

    @Mock
    private ImportConfig config;

    @Mock
    private Parser parser;

    @Mock
    private DateTimeService dateTimeService;

    @Captor
    private ArgumentCaptor<List<QuestionImportItem>> captor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDoJob() throws Exception {

        List<QuestionImportItem> questionImportItems = Arrays.asList(
            createQuestionImportItem("MPQ2E", "Ska Sverige ha ID-kontroller?", "2015-11-20"),
            createQuestionImportItem("MPQ2F", "Ska Sverige få ha avtal med Diktaturer?", "2016-03-20"),
            createQuestionImportItem("MPQ2G", "Ska Sverige utöka försvarsbudgeten med x antal kr?", "2016-03-23")
        );

        String dokumentlistaUrl = "http://test.riksdagen.se/dokumentlista/";

        when(config.getDokumentlistaUrl()).thenReturn(dokumentlistaUrl);
        when(config.getPeriod()).thenReturn(Period.ofMonths(1));
        when(parser.parseQuestions(any())).thenReturn(questionImportItems);
        when(dateTimeService.today()).thenReturn(LocalDate.of(2016, 2, 15));

        new ImportQuestionsJob(dao, config, parser, dateTimeService).doJob();

        verify(parser).parseQuestions(argThat(new CustomTypeSafeMatcher<URL>("URL match") {
            @Override
            protected boolean matchesSafely(URL url) {
                return dokumentlistaUrl.contains(url.getHost()) && dokumentlistaUrl.contains(url.getPath());
            }
        }));

        verify(dao).batchInsertQuestions(captor.capture());

        List<QuestionImportItem> items = captor.getValue();
        assertThat(items, hasSize(3));
        assertThat(items, equalTo(questionImportItems));
    }

    private QuestionImportItem createQuestionImportItem(String riksdagsId, String title, String closeTime) {
        Instant instant = Instant.parse(closeTime + "T00:00:00Z");
        return new QuestionImportItem(riksdagsId, title, false, Optional.of(instant), Optional.empty(), Optional.empty());
    }
}