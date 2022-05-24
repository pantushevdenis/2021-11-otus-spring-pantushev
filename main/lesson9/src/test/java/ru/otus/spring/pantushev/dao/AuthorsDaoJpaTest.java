package ru.otus.spring.pantushev.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.pantushev.domain.Author;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DAO Authors")
@DataJpaTest
@Import(
        {
                AuthorsDaoJpa.class
        }
)
class AuthorsDaoJpaTest {
    @Autowired
    AuthorsDaoJpa dao;

    @Test
    @DisplayName("бин AuthorsDaoJpa инициализирован")
    void shouldInitiatedAuthorsDaoJdbcBean() {
        assertNotNull(dao);
    }

    @Test
    @DisplayName("Список одержит только два определенных автора")
    void shouldReturnGetAll() {
        List<Author>actualList = dao.getAll();
        assertThat(actualList)
                .containsExactlyInAnyOrder(
                        new Author(1, "Author1"),
                        new Author(2,"Author2")

                );
    }
}