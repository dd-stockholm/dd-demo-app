package org.dd.demoapp.delegate;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DelegateDbMapper implements ResultSetMapper<Delegate> {

    @Override
    public Delegate map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return Delegate.newInstance(r.getString("name"), r.getString("delegate_reference"), r.getString("description"), r.getString("logo_url"), r.getString("webpage_url"));
    }
}
