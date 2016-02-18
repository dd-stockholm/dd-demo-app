package org.dd.demoapp.riksdagen;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlBatch;
import org.skife.jdbi.v2.sqlobject.customizers.BatchChunkSize;

public abstract class ImportDAO {

    @BatchChunkSize(1000)
    @SqlBatch("merge into question (riksdagsId, question, closeTime) key(riksdagsId) values (:riksdagsId, :title, :closeTime)")
    abstract void batchInsertQuestions(@BindBean Iterable<QuestionImportItem> items);

}
