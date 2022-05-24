package ru.otus.spring.pantushev.services.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.spring.pantushev.dao.AuthorsDaoJdbc;
import ru.otus.spring.pantushev.dao.BooksDaoJdbc;
import ru.otus.spring.pantushev.dao.JenresDaoJdbc;
import ru.otus.spring.pantushev.domain.Book;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("service.data common test")
@JdbcTest
@Import(
        {
                AuthorsDaoJdbc.class,
                JenresDaoJdbc.class,
                BooksDaoJdbc.class,
                CommonServiceImpl.class
        }
)

public class CommonServiceImplTest {
    @Autowired
    BooksDaoJdbc dao;

    @MockBean
    AuthorsDaoJdbc authorsDaoJdbc;

    @MockBean
    JenresDaoJdbc jenresDaoJdbc;

    @Autowired
    CommonServiceImpl commonService;

    @Test
    @DisplayName("бин dao инициализирован")
    void shouldInitiatedBooksDaoJdbcBean() {
        assertNotNull(dao);
    }

    @Test
    @DisplayName("бин commonService инициализирован")
    void shouldInitiateCommonServiceImplBean() {
        assertNotNull(commonService);
    }

    @Test
    @DisplayName("Должен обновить все поля")
    void shouldUpdateBookAllFields() {
        commonService.updateBook(1L, "Some book", 2L, 2L, 2005);

        assertThat(dao.getAll())
                .containsExactlyInAnyOrder(
                        new Book(1L, "Some book", 2L, 2L, 2005),
                        new Book(2L, "Book2", 2L, 1L, 2001),
                        new Book(3L, "Book3", 2L, 2L, 2002)
                );
    }

    @Test
    @DisplayName("Должен обновить поле год издания в первой книге, остальные поля не должны изменяться")
    void shouldUpdateBookOneField() {
        commonService.updateBook(1L, null, null, null, 2005);

        assertThat(dao.getAll())
                .containsExactlyInAnyOrder(
                        new Book(1L, "Book1", 1L, 1L, 2005),
                        new Book(2L, "Book2", 2L, 1L, 2001),
                        new Book(3L, "Book3", 2L, 2L, 2002)
                );
    }


}
