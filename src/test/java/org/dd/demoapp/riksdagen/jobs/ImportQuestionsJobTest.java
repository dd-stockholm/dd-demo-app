package org.dd.demoapp.riksdagen.jobs;

import org.dd.demoapp.config.ImportConfig;
import org.dd.demoapp.riksdagen.QuestionImportItem;
import org.dd.demoapp.riksdagen.betankande.Parser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
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

        assertThat(captor.getValue(), hasSize(3));
    }
}