package ru.otus.spring.pantushev.services.data;

import ru.otus.spring.pantushev.domain.*;

import java.util.List;

public interface CommonService {
    List<Author> getAuthorAll();
    Author getAuthorById(long id);
    List<Jenre> getJenreAll();
    Jenre getJenreById(long id);
    List<Book> getBookAll();
    long getBookCount();
    Book getBookById(long id);
    Book insertBook(Book book);
    void updateBook(Book book);
    void updateBook(
            long id,
            String name,
            Long authorId,
            Long jenreId,
            Integer publishingYear
    );


    List<Comment> getCommentsByBook(long bookId);
    List<Comment> getCommentsByBook(Book book);
}
