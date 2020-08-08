package org.dd.demoapp.question;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;

public class QuestionDbMapper implements ResultSetMapper<Question> {

    @Override
    public Question map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        Instant closeTime = r.getTimestamp("closeTime").toInstant();
        return Question.newInstance(r.getString("id"), r.getString("riksdagsId"), r.getString("question"), closeTime);
    }

}
