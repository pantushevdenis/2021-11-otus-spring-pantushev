package ru.otus.spring.pantushev.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.pantushev.domain.Author;
import ru.otus.spring.pantushev.domain.Jenre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JenresDaoJdbc
    implements JenresDao
{
    private final NamedParameterJdbcOperations jdbc;

    @Autowired
    public JenresDaoJdbc(NamedParameterJdbcOperations jdbcOperations)
    {
        this.jdbc = jdbcOperations;
    }

    @Override
    public List<Jenre> getAll() {
        return jdbc.query("select ID, Name from JENRES", new JenresDaoJdbc.JenresMapper());
    }


    private static class JenresMapper
            implements RowMapper<Jenre>
    {
        @Override
        public Jenre mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Jenre(
                    rs.getLong("ID"),
                    rs.getString("Name")
            );
        }
    }

}
