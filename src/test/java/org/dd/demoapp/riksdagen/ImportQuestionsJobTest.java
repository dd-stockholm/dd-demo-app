package org.dd.demoapp.riksdagen;

import org.dd.demoapp.TestDefaults;
import org.dd.demoapp.config.ImportConfig;
import org.dd.demoapp.riksdagen.ImportDAO;
import org.dd.demoapp.riksdagen.ImportQuestionsJob;
import org.dd.demoapp.riksdagen.QuestionImportItem;
import org.dd.demoapp.riksdagen.betankande.Parser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.mockito.Mockito.verify;

public class ImportQuestionsJobTest {

    @Mock
    private ImportDAO dao;

    @Mock
    private ImportConfig config;

    @Mock
    private Parser parser;

    @Captor
    private ArgumentCaptor<List<QuestionImportItem>> captor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test // todo: extend this test when it uses import data
    public void testDoJob() throws Exception {
        ImportQuestionsJob job = new ImportQuestionsJob(dao, config, parser);
        job.doJob();

        verify(dao).batchInsertQuestions(captor.capture());

        List<QuestionImportItem> items = captor.getValue();
        assertThat(items, hasSize(3));
        assertThat(items, everyItem(
            allOf(
                hasProperty("riksdagsId", notNullValue()),
                hasProperty("title", notNullValue()),
                hasProperty("closeTime", is(new TestDefaults.OptionalIsPresentMatcher<>())),
                hasProperty("decided", is(false))
            )
        ));
    }
}