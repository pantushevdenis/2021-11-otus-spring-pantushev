package ru.otus.spring.pantushev.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.spring.pantushev.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AuthorsDaoJdbc
    implements AuthorsDao
{
    private final NamedParameterJdbcOperations jdbc;

    @Autowired
    public AuthorsDaoJdbc(NamedParameterJdbcOperations jdbcOperations)
    {
        this.jdbc = jdbcOperations;
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select ID, Name from AUTHORS", new AuthorsMapper());
    }

    private static class AuthorsMapper
        implements RowMapper<Author>
    {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Author(
                    rs.getLong("ID"),
                    rs.getString("Name")
            );
        }
    }
}
