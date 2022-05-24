package ru.otus.spring.pantushev.services.data;

import ru.otus.spring.pantushev.domain.Author;
import ru.otus.spring.pantushev.domain.Book;
import ru.otus.spring.pantushev.domain.Comment;
import ru.otus.spring.pantushev.domain.Jenre;

import java.util.List;
import java.util.Optional;

public interface CommonService {
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
