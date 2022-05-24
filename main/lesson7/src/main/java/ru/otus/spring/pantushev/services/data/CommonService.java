package ru.otus.spring.pantushev.services.data;

import ru.otus.spring.pantushev.domain.Author;
import ru.otus.spring.pantushev.domain.Book;
import ru.otus.spring.pantushev.domain.Jenre;
import ru.otus.spring.pantushev.domain.views.BookViewAll;

import java.util.List;

public interface CommonService {
    List<Author> getAuthorAll();
    List<Jenre> getJenreAll();
    List<Book> getBookAll();
    int getBookCount();
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
    List<BookViewAll> getBookViewAll();


}
