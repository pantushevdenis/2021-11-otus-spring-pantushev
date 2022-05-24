package ru.otus.spring.pantushev.dao;

import ru.otus.spring.pantushev.domain.Book;
import ru.otus.spring.pantushev.domain.views.BookViewAll;

import java.util.List;

public interface BooksDao {
    int getCount();
    List<Book> getAll();
    List<BookViewAll> getAllBookViewAll();
    Book insert(Book book);
    Book getById(long ID);
    void update(Book book);
    void delete(long ID);
}
