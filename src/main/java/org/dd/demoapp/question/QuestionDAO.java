package org.dd.demoapp.question;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import javax.annotation.Resource;
import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.util.List;

@Resource
@Singleton
@RegisterMapper(QuestionDbMapper.class)
public interface QuestionDAO {

    @SqlUpdate("create table if not exists question (id bigint identity, question varchar, closeTime timestamp)")
    void createDb();

    @SqlUpdate("insert into question (question, closeTime) values (:question, :closeTime)")
    void insertRow(@Bind("question") String question, @Bind("closeTime") LocalDateTime closeTime);

    @SqlQuery("select id, question, closeTime from question where closeTime > :closeTime")
    List<Question> questions(@Bind("closeTime") LocalDateTime closeTime);

}
