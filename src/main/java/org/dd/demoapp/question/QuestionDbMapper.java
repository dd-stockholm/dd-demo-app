package org.dd.demoapp.question;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionDbMapper implements ResultSetMapper<Question> {

    @Override
    public Question map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        String closeTime = r.getTimestamp("closeTime").toString();
        return new Question(r.getString("id"), r.getString("question"), closeTime);
    }

}
