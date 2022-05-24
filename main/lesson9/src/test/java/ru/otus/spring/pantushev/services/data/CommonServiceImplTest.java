package ru.otus.spring.pantushev.services.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.pantushev.dao.*;
import ru.otus.spring.pantushev.domain.Author;
import ru.otus.spring.pantushev.domain.Book;
import ru.otus.spring.pantushev.domain.Comment;
import ru.otus.spring.pantushev.domain.Jenre;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("service.data common test")
@DataJpaTest
@Import(
        {
                AuthorsDaoJpa.class,
                JenresDaoJpa.class,
                BooksDaoJpa.class,
                CommonServiceImpl.class
        }
)
public class CommonServiceImplTest {
    @Autowired
    BooksDaoJpa booksDaoJpa;

    @Autowired
    AuthorsDaoJpa authorsDaoJpa;

    @Autowired
    JenresDaoJpa jenresDaoJpa;

    @Autowired
    CommonServiceImpl commonService;

    @Test
    @DisplayName("бин dao book инициализирован")
    void shouldInitiatedBooksDaoBean() {
        assertNotNull(booksDaoJpa);
    }

    @Test
    @DisplayName("бин dao author инициализирован")
    void shouldInitiatedAuthorsDaoBean() {
        assertNotNull(authorsDaoJpa);
    }

    @Test
    @DisplayName("бин dao jenres инициализирован")
    void shouldInitiatedJenresDaoBean() {
        assertNotNull(jenresDaoJpa);
    }


    @Test
    @DisplayName("бин commonService инициализирован")
    void shouldInitiateCommonServiceImplBean() {
        assertNotNull(commonService);
    }

    @Test
    @DisplayName("Должен обновить все поля")
    void shouldUpdateBookAllFields() {
        Author a1 = new Author(1L, "Author1");
        Author a2 = new Author(2L, "Author2");
        Jenre j1 = new Jenre(1L, "Jenre1");
        Jenre j2 = new Jenre(2L, "Jenre2");
        Comment c1_1 = new Comment(1L, "Comment1_1"/*, 1L*/);
        Comment c1_2 = new Comment(2L, "Comment1_2"/*, 1L*/);
        Comment c1_3 = new Comment(3L, "Comment1_3"/*, 1L*/);
        Comment c2_1 = new Comment(3L, "Comment2_1"/*, 2L*/);

        commonService.updateBook(1L, "Some book", 2L, 2L, 2005);

        assertThat(booksDaoJpa.getAll())
                .containsExactlyInAnyOrder(
                        new Book(1L, "Some book", a2, j2, 2005, Arrays.asList(c1_1, c1_2, c1_3)),
                        new Book(2L, "Book2", a2, j1, 2001, Arrays.asList(c2_1)),
                        new Book(3L, "Book3", a2, j2, 2002, Arrays.asList())
                );
    }

    @Test
    @DisplayName("Должен обновить поле год издания в первой книге, остальные поля не должны изменяться")
    void shouldUpdateBookOneField() {
        Author a1 = new Author(1L, "Author1");
        Author a2 = new Author(2L, "Author2");
        Jenre j1 = new Jenre(1L, "Jenre1");
        Jenre j2 = new Jenre(2L, "Jenre2");

        commonService.updateBook(1L, null, null, null, 2005);

        assertThat(booksDaoJpa.getAll())
                .containsExactlyInAnyOrder(
                        Book.builder("Book1", a1, j1).id(1L).publishingYear(2005).build(),
                        Book.builder("Book2", a2, j1).id(2L).publishingYear(2001).build(),
                        Book.builder("Book3", a2, j2).id(3L).publishingYear(2002).build()
                );
    }

    @Test
    @DisplayName("Должен возвратить все комменты для первой книги по идентификатору книги")
    void shouldGetAllCommentsFoFirstBookById() {
        Author a1 = new Author(1L, "Author1");
        Author a2 = new Author(2L, "Author2");
        Jenre j1 = new Jenre(1L, "Jenre1");
        Jenre j2 = new Jenre(2L, "Jenre2");

        Comment c1_1 = new Comment(1L, "Comment1_1"/*, 1L*/);
        Comment c1_2 = new Comment(2L, "Comment1_2"/*, 1L*/);
        Comment c1_3 = new Comment(3L, "Comment1_3"/*, 1L*/);
        Comment c2_1 = new Comment(3L, "Comment2_1"/*, 2L*/);

        Book b1 = new Book(1L, "Some book", a2, j2, 2000, Arrays.asList(c1_1, c1_2, c1_3));
        Book b2 = new Book(2L, "Book2", a2, j1, 2001, Arrays.asList(c2_1));
        Book b3 = new Book(3L, "Book3", a2, j2, 2002, Arrays.asList());
        c1_1.setBook(b1);
        c1_2.setBook(b1);
        c1_3.setBook(b1);
        c2_1.setBook(b2);

        List<Comment> comments = commonService.getCommentsByBook(1L);
        assertThat(comments)
                .containsExactlyInAnyOrder(c1_1, c1_2, c1_3);
    }

    @Test
    @DisplayName("Должен возвратить все комменты для первой книги по объекту книги, уже загруженной")
    void shouldGetAllCommentsForFirstBookByObject() {
        Author a1 = new Author(1L, "Author1");
        Author a2 = new Author(2L, "Author2");
        Jenre j1 = new Jenre(1L, "Jenre1");
        Jenre j2 = new Jenre(2L, "Jenre2");

        Comment c1_1 = new Comment(1L, "Comment1_1"/*, 1L*/);
        Comment c1_2 = new Comment(2L, "Comment1_2"/*, 1L*/);
        Comment c1_3 = new Comment(3L, "Comment1_3"/*, 1L*/);
        Comment c2_1 = new Comment(3L, "Comment2_1"/*, 2L*/);

        Book b1 = new Book(1L, "Some book", a2, j2, 2000, Arrays.asList(c1_1, c1_2, c1_3));
        Book b2 = new Book(2L, "Book2", a2, j1, 2001, Arrays.asList(c2_1));
        Book b3 = new Book(3L, "Book3", a2, j2, 2002, Arrays.asList());
        c1_1.setBook(b1);
        c1_2.setBook(b1);
        c1_3.setBook(b1);
        c2_1.setBook(b2);

        Book book = commonService.getBookById(1L);
        List<Comment> comments = commonService.getCommentsByBook(book);
        assertThat(comments)
                .containsExactlyInAnyOrder(c1_1, c1_2, c1_3);
    }



}
