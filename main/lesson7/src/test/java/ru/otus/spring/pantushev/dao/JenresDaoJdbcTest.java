package ru.otus.spring.pantushev.dao;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.pantushev.domain.Author;
import ru.otus.spring.pantushev.domain.Jenre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("DAO Jenres")
@JdbcTest
@Import(
        JenresDaoJdbc.class
)
public class JenresDaoJdbcTest {
    @Autowired
    JenresDaoJdbc dao;

    @Test
    @DisplayName("бин JenresDaoJdbc инициализирован")
    void shouldInitiatedJenresDaoJdbcBean() {
        assertNotNull(dao);
    }

    @Test
    @DisplayName("Список одержит только два определенных жанра")
    void shouldReturnGetAll() {
        List<Jenre> actualList = dao.getAll();
        assertThat(actualList)
                .containsExactlyInAnyOrder(
                        new Jenre(1, "Jenre1"),
                        new Jenre(2,"Jenre2")

                );
    }
}
