package ru.otus.spring.pantushev.domain;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Домен Comment")
public class CommentTest {
    @Test
    @DisplayName("Должен возвращать GetBookId != 0 для коммента с книгой")
    void shouldReturnCorrectBookIdWithValueBook() throws ParseException {
        LocalDate ld = LocalDate.of (2001, 1, 10);

        Author a1 = new Author(1L, "Author1", "Author1", ld);
        Author a2 = new Author(2L, "Author2", "Author2", ld);
        Jenre j1 = new Jenre(1L, "Jenre1");
        Jenre j2 = new Jenre(2L, "Jenre2");
        Book b1 = new Book(1L, "Book1", a1, j1, 2000);
        Book b2 = new Book(1L, "Book2", a2, j1, 2001);
        Book b3 = new Book(3L, "Book3", a2, j2, 2002);

        Comment c1_1 = new Comment(1L, "Comment1_1", b1);
        long bi = c1_1.getBookId();

        assertEquals(1L, bi);
    }

    @Test
    @DisplayName("Должен возвращать GetBookId == 0 для коммента без книги")
    void shouldReturnCorrectBookIdWithNotValueBook() throws ParseException {
        LocalDate ld = LocalDate.of (2001, 1, 10);

        Author a1 = new Author(1L, "Author1", "Author1", ld);
        Author a2 = new Author(2L, "Author2", "Author2", ld);
        Jenre j1 = new Jenre(1L, "Jenre1");
        Jenre j2 = new Jenre(2L, "Jenre2");
        Book b1 = new Book(1L, "Book1", a1, j1, 2000);
        Book b2 = new Book(1L, "Book2", a2, j1, 2001);
        Book b3 = new Book(3L, "Book3", a2, j2, 2002);

        Comment c1_1 = new Comment(1L, "Comment1_1");
        long bi = c1_1.getBookId();

        assertEquals(0, bi);
    }


}
