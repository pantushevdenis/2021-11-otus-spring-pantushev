package ru.otus.spring.pantushev.services.data;

import org.springframework.data.jpa.repository.EntityGraph;
import ru.otus.spring.pantushev.domain.*;

import java.util.List;
import java.util.Optional;

public interface CommonService {
    Iterable<Author> getAuthorAll();
    Optional<Author> getAuthorById(long id);
    Iterable<Jenre> getJenreAll();
    Optional<Jenre> getJenreById(long id);
    Iterable<Book> getBookAll();
    long getBookCount();
    Optional<Book> getBookById(long id);
    Book saveBook(Book book);
    void updateBook(
            long id,
            String name,
            Long authorId,
            Long jenreId,
            Integer publishingYear
    );

    Iterable<Book> findBooksByJenreLike(String name);
    Iterable<Book> findBooksByAuthorLike(String name);

    List<Comment> getCommentsByBook(long bookId);
    List<Comment> getCommentsByBook(Book book);



}
