package ru.otus.spring.pantushev.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.pantushev.domain.Book;
import ru.otus.spring.pantushev.domain.views.BookViewAll;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DAO Books")
@JdbcTest
@Import(
        BooksDaoJdbc.class
)
class BooksDaoJdbcTest {
    @Autowired
    BooksDaoJdbc dao;

    @Test
    @DisplayName("бин BooksDaoJdbc инициализирован")
    void shouldInitiatedBooksDaoJdbcBean() {
        assertNotNull(dao);
    }

    @Test
    @DisplayName("Должен возвращать количество книг- 3")
    void shouldGetCountReturn3() {
        int c = dao.getCount();
        assertEquals(3, c);
    }

    @Test
    @DisplayName("Список одержит только три определенных книги")
    void shouldReturnGetAll3Books() {
        List<Book> actualList = dao.getAll();
        assertThat(actualList)
                .containsExactlyInAnyOrder(
                        new Book(1L, "Book1", 1L, 1L, 2000),
                        new Book(2L,"Book2", 2L, 1L, 2001),
                        new Book(3L,"Book3",2L, 2L, 2002)
                );
    }



    @Test
    @DisplayName("Должен возвратить список из трех книг со всеми полями справочника")
    void shouldetAllFullReturn3Books() {
        List<BookViewAll> actualList= dao.getAllBookViewAll();
        assertThat(actualList)
                .containsExactlyInAnyOrder(
                        new BookViewAll(1L, "Book1", 1L, "Author1", 1L, "Jenre1", 2000),
                        new BookViewAll(2L, "Book2", 2L, "Author2", 1L, "Jenre1", 2001),
                        new BookViewAll(3L, "Book3", 2L, "Author2", 2L, "Jenre2", 2002)
                );
    }

    @Test
    @DisplayName("Должен вернуть первую книгу")
    void shouldGetFirstBook() {
        Book actualBook = dao.getById(1L);
        Assertions.assertEquals(new Book(1L, "Book1", 1L, 1L, 2000), actualBook);
    }

    @Test
    @DisplayName("Должен вставить одну книгу, количество книг должно+1, книга должна быть в таблице")
    void shouldInsertBook() {
        assertEquals(3, dao.getCount());
        Book newValue = new Book("Book4", 1L, 2L, 2005);
        Book afterInsert = dao.insert(newValue);

        Book expectedValue = new Book(4L, "Book4", 1L, 2L, 2005);
        assertEquals(expectedValue, afterInsert);
        assertEquals(4, dao.getCount());
        assertEquals(expectedValue, dao.getById(4L));
    }



    @Test
    @DisplayName("Должен обновить поле год издания в первой книге")
    void shouldUpdateBook() {
        Book newValue = dao.getById(1L);
        newValue.setPublishingYear(2005);
        dao.update(newValue);

        assertThat(dao.getAll())
                .containsExactlyInAnyOrder(
                        new Book(1L, "Book1", 1L, 1L, 2005),
                        new Book(2L, "Book2", 2L, 1L, 2001),
                        new Book(3L, "Book3", 2L, 2L, 2002)
                );
    }

    @Test
    @DisplayName("Должен удалить первую книгу")
    void shouldDeleteFirstBook() {
        assertEquals(3, dao.getCount());
        dao.delete(1L);
        assertThat(dao.getAll())
                .containsExactlyInAnyOrder(
                        new Book(2L,"Book2", 2L, 1L, 2001),
                        new Book(3L,"Book3",2L, 2L, 2002)
                );
    }
}