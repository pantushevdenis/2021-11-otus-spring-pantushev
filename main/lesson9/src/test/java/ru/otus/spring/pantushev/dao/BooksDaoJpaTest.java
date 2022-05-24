package ru.otus.spring.pantushev.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.pantushev.domain.Author;
import ru.otus.spring.pantushev.domain.Book;
import ru.otus.spring.pantushev.domain.Comment;
import ru.otus.spring.pantushev.domain.Jenre;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DAO Books")
@DataJpaTest
@Import(
        {
                AuthorsDaoJpa.class,
                JenresDaoJpa.class,
                BooksDaoJpa.class
        }
)
class BooksDaoJpaTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    BooksDaoJpa booksDaoJpa;

    @Test
    @DisplayName("EntityContext инициализирован")
    void shoulInitiateentityManager() {
        assertNotNull(em);
    }

    @Test
    @DisplayName("бин BooksDaoJdbc инициализирован")
    void shouldInitiatedBooksDaoJdbcBean() {
        assertNotNull(booksDaoJpa);
    }

    @Test
    @DisplayName("Должен возвращать количество книг- 3")
    void shouldGetCountReturn3() {
        long c = booksDaoJpa.getCount();
        assertEquals(3L, c);
    }

    @Test
    @DisplayName("Список одержит только три определенных книги (комменты грузиться не должны, Author и Jenre должны грузиться в один запрос)")
    void shouldReturnGetAll3Books() {
        Author a1 = new Author(1L, "Author1");
        Author a2 = new Author(2L, "Author2");
        Jenre j1 = new Jenre(1L, "Jenre1");
        Jenre j2 = new Jenre(2L, "Jenre2");

        List<Book> actualList = booksDaoJpa.getAll();
        assertThat(actualList)
                .containsExactlyInAnyOrder(
                        new Book(1L, "Book1", a1, j1, 2000, null),
                        new Book(2L, "Book2", a2, j1, 2001, null),
                        new Book(3L, "Book3", a2, j2, 2002, null)
                );
    }

    @Test
    @DisplayName("Выбрать список, явно загрузить комменты первой книги")
    void shouldReturnGetAll3BooksLoadComments() {
        Author a1 = new Author(1L, "Author1");
        Author a2 = new Author(2L, "Author2");
        Jenre j1 = new Jenre(1L, "Jenre1");
        Jenre j2 = new Jenre(2L, "Jenre2");

        List<Book> actualList = booksDaoJpa.getAll();
        List<Comment> cs1 = actualList.get(0).getComments();
    }



    @Test
    @DisplayName("Должен вернуть первую книгу (комменты грузиться не должны, Author и Jenre должны грузиться в один запрос)")
    void shouldGetFirstBook() {
        Author a1 = new Author(1L, "Author1");
        Author a2 = new Author(2L, "Author2");
        Jenre j1 = new Jenre(1L, "Jenre1");
        Jenre j2 = new Jenre(2L, "Jenre2");

        Book actualBook = booksDaoJpa.getById(1L);
        assertEquals(Book.builder("Book1", a1, j1).id(1L).publishingYear(2000).build(), actualBook);
    }


    @Test
    @DisplayName("Должен вернуть первую книгу, явно загрузить комменты")
    void shouldGetFirstBookandComments() {
        Book actualBook = booksDaoJpa.getById(1L);

        List<Comment> comments = actualBook.getComments();
        Comment comment = comments.get(0);
        assertNotNull(comment);
        assertEquals(3, comments.size());
        long commentId = comment.getId();
        assertEquals(1, commentId);
        String commentContent = comment.getContent();
        assertEquals("Comment1_1", commentContent);

    }

    @Test
    @DisplayName("Должен вернуть первую книгу, явно загрузить  поля Author, Jenre")
    void shouldGetFirstBookandDictFields() {
        Book actualBook = booksDaoJpa.getById(1L);

        Author author = actualBook.getAuthor();
         long authorId = author.getId();
         assertEquals(1, authorId);
         String authorName = author.getName();
         assertEquals("Author1", authorName);

         Jenre jenre = actualBook.getJenre();
         long jenreId = jenre.getId();
         assertEquals(1, jenreId);
         String jenreName = jenre.getName();
         assertEquals("Jenre1", jenreName);
    }

    @Test
    @DisplayName("Должен вставить одну книгу, количество книг должно+1, книга должна быть в таблице")
    void shouldInsertBook() {
        Author a1 = new Author(1L, "Author1");
        Author a2 = new Author(2L, "Author2");
        Jenre j1 = new Jenre(1L, "Jenre1");
        Jenre j2 = new Jenre(2L, "Jenre2");

        assertEquals(3, booksDaoJpa.getCount());
        Book newValue = Book.builder("Book4", a1, j2).publishingYear(2005).build();
        Book afterInsert = booksDaoJpa.insert(newValue);


        Book expectedValue = Book.builder("Book4", a1, j2).id(4L).publishingYear(2005).build();
        assertEquals(expectedValue, afterInsert);
        assertEquals(4, booksDaoJpa.getCount());
        assertEquals(expectedValue, booksDaoJpa.getById(4L));
    }

    @Test
    @DisplayName("Должен обновить поле год издания в первой книге")
    void shouldUpdateBook() {
        Author a1 = new Author(1L, "Author1");
        Author a2 = new Author(2L, "Author2");
        Jenre j1 = new Jenre(1L, "Jenre1");
        Jenre j2 = new Jenre(2L, "Jenre2");

        Book newValue = booksDaoJpa.getById(1L);
        newValue.setPublishingYear(2005);
        booksDaoJpa.update(newValue);

        assertThat(booksDaoJpa.getAll())
                .containsExactlyInAnyOrder(
                        Book.builder("Book1", a1, j1).id(1L).publishingYear(2005).build(),
                        Book.builder("Book2", a2, j1).id(2L).publishingYear(2001).build(),
                        Book.builder("Book3", a2, j2).id(3L).publishingYear(2002).build()
                );
    }


    @Test
    @DisplayName("Должен удалить первую книгу")
    void shouldDeleteFirstBook() {
        Author a1 = new Author(1L, "Author1");
        Author a2 = new Author(2L, "Author2");
        Jenre j1 = new Jenre(1L, "Jenre1");
        Jenre j2 = new Jenre(2L, "Jenre2");

        assertEquals(3, booksDaoJpa.getCount());
        booksDaoJpa.delete(1L);
        List<Book> booksActual = booksDaoJpa.getAll();
        assertThat(booksActual)
                .containsExactlyInAnyOrder(
                        Book.builder("Book2", a2, j1).id(2L).publishingYear(2001).build(),
                        Book.builder("Book3", a2, j2).id(3L).publishingYear(2002).build()
                );
    }

    @Test
    @DisplayName("Удаление Комментариев каскадом с удалением книги")
    void shoildCascadeDeleteConnentsOnDeleteBook() {
        assertEquals(4, em.createQuery("select count(*) from Comment", Long.class).getSingleResult());

        booksDaoJpa.delete(1L);

        long cAfter = em.createQuery("select count(*) from Comment", Long.class).getSingleResult();
        assertEquals(1,cAfter);
    }

    @Test
    @DisplayName("Вывести в текстовом виде, не впадая в stackverflow")
    void shouldCorrecttoString() {
        Book b = booksDaoJpa.getById(1L);
        String s = b.toString();
    }


}