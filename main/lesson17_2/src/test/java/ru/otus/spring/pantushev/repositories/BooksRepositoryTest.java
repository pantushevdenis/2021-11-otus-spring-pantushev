package ru.otus.spring.pantushev.repositories;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.spring.pantushev.domain.Author;
import ru.otus.spring.pantushev.domain.Book;
import ru.otus.spring.pantushev.domain.Comment;
import ru.otus.spring.pantushev.domain.Jenre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("repository Books")
@DataJpaTest
class BooksRepositoryTest {
    @Autowired
    BooksRepository booksRepository;

    @Test
    @DisplayName("бин booksRepository инициализирован")
    void shouldInitiatedBooksDaoJdbcBean() {
        assertNotNull(booksRepository);
    }

    @Test
    @DisplayName("Должен возвращать количество книг- 3")
    void shouldGetCountReturn3() {
        long c = booksRepository.count();
        assertEquals(3L, c);
    }

    @Test
    @DisplayName("Список одержит только три определенных книги (комменты грузиться не должны, Author и Jenre должны грузиться в один запрос)")
    void shouldReturnGetAll3Books() throws ParseException {
        LocalDate ld = LocalDate.of (2001, 1, 10);

        Author a1 = new Author(1L, "Author1", "Author1", ld);
        Author a2 = new Author(2L, "Author2", "Author2", ld);
        Jenre j1 = new Jenre(1L, "Jenre1");
        Jenre j2 = new Jenre(2L, "Jenre2");

        Iterable<Book> actualList = booksRepository.findAll();
        assertThat(actualList)
                .containsExactlyInAnyOrder(
                        new Book(1L, "Book1", a1, j1, 2000, null),
                        new Book(2L, "Book2", a2, j1, 2001, null),
                        new Book(3L, "Book3", a2, j2, 2002, null)
                );
    }

    @Test
    @DisplayName("Выбрать список, явно загрузить комменты первой книги, затем второй")
    void shouldReturnGetAll3BooksLoadComments() throws ParseException {
        LocalDate ld = LocalDate.of (2001, 1, 10);

        Author a1 = new Author(1L, "Author1", "Author1", ld);
        Author a2 = new Author(2L, "Author2", "Author2", ld);
        Jenre j1 = new Jenre(1L, "Jenre1");
        Jenre j2 = new Jenre(2L, "Jenre2");

        Comment c1_1 = new Comment(1L, "Comment1_1"/*, 1L*/);
        Comment c1_2 = new Comment(2L, "Comment1_2"/*, 1L*/);
        Comment c1_3 = new Comment(3L, "Comment1_3"/*, 1L*/);
        Comment c2_1 = new Comment(4L, "Comment2_1"/*, 2L*/);

        Book b1 = new Book(1L, "Some book", a2, j2, 2000, Arrays.asList(c1_1, c1_2, c1_3));
        Book b2 = new Book(2L, "Book2", a2, j1, 2001, Arrays.asList(c2_1));
        Book b3 = new Book(3L, "Book3", a2, j2, 2002, Arrays.asList());
        c1_1.setBook(b1);
        c1_2.setBook(b1);
        c1_3.setBook(b1);
        c2_1.setBook(b2);

        Iterable<Book> actualList = booksRepository.findAll();
        assertNotNull(actualList);
        Iterator<Book> bi = actualList.iterator();
        Book fb = bi.next();
        List<Comment> cl = fb.getComments();
        assertNotNull(cl);

        Iterator<Comment> ci = cl.iterator();
        assertThat(ci.next())
                .isEqualTo(c1_1);
        assertThat(ci.next())
            .isEqualTo(c1_2);
        assertThat(ci.next())
            .isEqualTo(c1_3);

        cl = bi.next().getComments();
        assertNotNull(cl);
    }


    @Test
    @DisplayName("Должен вернуть первую книгу (комменты грузиться не должны, Author и Jenre должны грузиться в один запрос)")
    void shouldGetFirstBook() throws ParseException {
        LocalDate ld = LocalDate.of (2001, 1, 10);

        Author a1 = new Author(1L, "Author1", "Author1", ld);
        Author a2 = new Author(2L, "Author2", "Author2", ld);
        Jenre j1 = new Jenre(1L, "Jenre1");
        Jenre j2 = new Jenre(2L, "Jenre2");

        Optional<Book> actualBook = booksRepository.findById(1L);
        assertThat(actualBook).isNotEmpty();
        assertEquals(new Book(1L, "Book1", a1, j1, 2000), actualBook.get());
    }


    @Test
    @DisplayName("Должен вернуть первую книгу, явно загрузить комменты")
    void shouldGetFirstBookandComments() {
        Optional<Book> actualBook = booksRepository.findById(1L);

        List<Comment> comments = actualBook.get().getComments();
        Comment comment = comments.get(0);
        assertNotNull(comment);
        assertEquals(3, comments.size());
        long commentId = comment.getId();
        assertEquals(1, commentId);
        String commentContent = comment.getContent();
        assertEquals("Comment1_1", commentContent);

    }

    @Test
    @DisplayName("Найти книги с автором с похожим названием")
    void shouldFindBooksAuthorLIke() throws ParseException {
        LocalDate ld = LocalDate.of (2001, 1, 10);

        Author a1 = new Author(1L, "Author1", "Author1", ld);
        Author a2 = new Author(2L, "Author2", "Author2", ld);
        Jenre j1 = new Jenre(1L, "Jenre1");
        Jenre j2 = new Jenre(2L, "Jenre2");

        Comment c1_1 = new Comment(1L, "Comment1_1"/*, 1L*/);
        Comment c1_2 = new Comment(2L, "Comment1_2"/*, 1L*/);
        Comment c1_3 = new Comment(3L, "Comment1_3"/*, 1L*/);
        Comment c2_1 = new Comment(4L, "Comment2_1"/*, 2L*/);

        Book b1 = new Book(1L, "Some book", a2, j2, 2000, Arrays.asList(c1_1, c1_2, c1_3));
        Book b2 = new Book(2L, "Book2", a2, j1, 2001, Arrays.asList(c2_1));
        Book b3 = new Book(3L, "Book3", a2, j2, 2002, Arrays.asList());

        c1_1.setBook(b1);
        c1_2.setBook(b1);
        c1_3.setBook(b1);
        c2_1.setBook(b2);

        Iterable<Book> actualList = booksRepository.findBooksByAuthor_FullNameLike("%or2%");
        assertThat(actualList)
            .containsExactlyInAnyOrder(
                b2,
                b3
            );
    }


    @Test
    @DisplayName("Найти книги с жанром с похожим названием")
    void shouldFindBooksJenreLike() throws ParseException {
        LocalDate ld = LocalDate.of (2001, 1, 10);

        Author a1 = new Author(1L, "Author1", "Author1", ld);
        Author a2 = new Author(2L, "Author2", "Author2", ld);
        Jenre j1 = new Jenre(1L, "Jenre1");
        Jenre j2 = new Jenre(2L, "Jenre2");

        Iterable<Book> actualList = booksRepository.findBooksByJenre_NameLike("%re1%");
        assertThat(actualList)
            .containsExactlyInAnyOrder(
                new Book(1L, "Book1", a1, j1, 2000, null),
                new Book(2L, "Book2", a2, j1, 2001, null)
            );
    }

    @Test
    @DisplayName("Вывести в текстовом виде, не впадая в stackverflow")
    void shouldCorrecttoString() {
        Book b = booksRepository.findById(1L).get();
        String s = b.toString();
    }


}