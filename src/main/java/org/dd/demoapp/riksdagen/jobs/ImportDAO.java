package org.dd.demoapp.riksdagen.jobs;

import org.dd.demoapp.riksdagen.QuestionImportItem;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlBatch;
import org.skife.jdbi.v2.sqlobject.customizers.BatchChunkSize;

public abstract class ImportDAO {

    @BatchChunkSize(1000)
    @SqlBatch("insert into question (question, closeTime) values (:title, :closeTime)")
    abstract void batchInsertQuestions(@BindBean Iterable<QuestionImportItem> items);

}
