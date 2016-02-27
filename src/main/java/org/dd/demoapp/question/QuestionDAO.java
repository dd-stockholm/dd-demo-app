package org.dd.demoapp.question;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.Transaction;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import javax.annotation.Resource;
import javax.inject.Singleton;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Resource
@Singleton
@RegisterMapper(QuestionDbMapper.class)
public abstract class QuestionDAO {

    @SqlUpdate("create table if not exists question (id bigint identity, question varchar, closeTime timestamp)")
    abstract void createTable();

    @SqlUpdate("insert into question (question, closeTime) values (:question, :closeTime)")
    abstract void insertRow(@Bind("question") String question, @Bind("closeTime") Instant closeTime);

    @SqlQuery("select id, question, closeTime from question where closeTime > :closeTime")
    abstract List<Question> questions(@Bind("closeTime") Instant closeTime);

    @Transaction
    public void initDb() {
       createTable();
       insertRow("Ska Sverige ha ID-kontroller?", LocalDateTime.of(2015, 11, 20, 23, 59).toInstant(ZoneOffset.UTC));
       insertRow("Ska Sverige få ha avtal med Diktaturer?", LocalDateTime.of(2016, 3, 20, 23, 59).toInstant(ZoneOffset.UTC));
       insertRow("Ska Sverige utöka försvarsbudgeten med x antal kr?", LocalDateTime.of(2016, 3, 23, 23, 59).toInstant(ZoneOffset.UTC));
    }
}
