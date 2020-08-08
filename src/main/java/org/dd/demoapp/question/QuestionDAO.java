package org.dd.demoapp.question;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.Transaction;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import javax.annotation.Resource;
import javax.inject.Singleton;
import java.time.Instant;
import java.util.List;

@RegisterMapper(QuestionDbMapper.class)
public abstract class QuestionDAO {

    @SqlUpdate("create table if not exists question (id bigint identity, riksdagsId varchar, question varchar, closeTime timestamp)")
    abstract void createTable();

    @SqlQuery("select id, riksdagsId, question, closeTime from question where closeTime > :closeTime")
    public abstract List<Question> questions(@Bind("closeTime") Instant closeTime);

    @Transaction
    public void initDb() {
       createTable();
    }
}
