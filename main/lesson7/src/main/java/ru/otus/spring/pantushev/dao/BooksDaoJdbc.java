package ru.otus.spring.pantushev.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.pantushev.domain.Book;
import ru.otus.spring.pantushev.domain.views.BookViewAll;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BooksDaoJdbc
    implements BooksDao
{
    private final NamedParameterJdbcOperations jdbc;

    @Autowired
    public BooksDaoJdbc(NamedParameterJdbcOperations jdbcOperations)
    {
        this.jdbc = jdbcOperations;
    }

    @Override
    public int getCount() {
        Integer count = jdbc.queryForObject("select count(*) from BOOKS", (Map<String, Object>)null, Integer.class);
        return count == null? 0: count;

    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select ID, Name, AuthorID, JenreID, PublishingYear from BOOKS", new RowBookMapper());
    }

    @Override
    public List<BookViewAll> getAllBookViewAll() {
        return jdbc.query("select b.ID, b.Name, b.AuthorID, b.JenreID, b.PublishingYear, a.Name as AuthorName, j.Name as JenreName " +
                        "from BOOKS b " +
                        "left join AUTHORS a ON a.ID = b.AuthorID " +
                        "left join JENRES j ON j.ID = b.JenreID",
                new RowBookViewAllMapper()
        );
    }

    @Override
    public Book insert(Book book) {
        KeyHolder kh = new GeneratedKeyHolder();

        MapSqlParameterSource ps = new MapSqlParameterSource();
        ps.addValue("Name", book.getName());
        ps.addValue("AuthorID", book.getAuthorID());
        ps.addValue("JenreID", book.getJenreID());
        ps.addValue("PublishingYear", book.getPublishingYear());

        jdbc.update("insert into  BOOKS (" +
                        "Name," +
                        "AuthorID, " +
                        "JenreID, " +
                        "PublishingYear " +
                        ") " +
                        "values ( " +
                        ":Name, " +
                        ":AuthorID, " +
                        ":JenreID, " +
                        ":PublishingYear " +
                        ")",
                ps,
                kh
        );
        book.setId(kh.getKey().longValue());
        return book;
    }

    @Override
    public Book getById(long ID) {
        return jdbc.queryForObject("select* from BOOKS where ID = :ID",
                Map.of("ID", ID),
                new RowBookMapper());
    }

    @Override
    public void update(Book book) {
        MapSqlParameterSource ps = new MapSqlParameterSource();
        ps.addValue("ID", book.getId());
        ps.addValue("Name", book.getName());
        ps.addValue("AuthorID", book.getAuthorID());
        ps.addValue("JenreID", book.getJenreID());
        ps.addValue("PublishingYear", book.getPublishingYear());
        jdbc.update("update BOOKS " +
                "set " +
                "Name= :Name, " +
                "AuthorID = :AuthorID, " +
                "JenreID = :JenreID, " +
                "PublishingYear = :PublishingYear " +
                "where ID = :ID",
                ps
        );
    }

    @Override
    public void delete(long ID) {
        Map<String, Object> p = Collections.singletonMap("ID", ID);
        jdbc.update("delete from BOOKS where ID = :ID", p);
    }

    private static class RowBookMapper
            implements RowMapper<Book>
    {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Book(
                    rs.getLong("ID"),
                    rs.getString("Name"),
                    rs.getLong("AuthorID"),
                    rs.getLong("JenreID"),
                    rs.getObject("PublishingYear", Integer.class)
            );
        }
    }

    private static class RowBookViewAllMapper
        implements RowMapper<BookViewAll>
    {
        @Override
        public BookViewAll mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new BookViewAll(
                    rs.getLong("ID"),
                    rs.getString("Name"),
                    rs.getLong("AuthorID"),
                    rs.getString("AuthorName"),
                    rs.getLong("JenreID"),
                    rs.getString("JenreName"),
                    rs.getObject("PublishingYear", Integer.class)
            );
        }
    }

}
