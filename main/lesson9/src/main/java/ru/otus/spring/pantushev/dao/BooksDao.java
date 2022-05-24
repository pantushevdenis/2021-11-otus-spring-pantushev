package ru.otus.spring.pantushev.dao;

import ru.otus.spring.pantushev.domain.Book;

import java.util.List;

public interface BooksDao {
    long getCount();
    List<Book> getAll();
    Book insert(Book book);
    Book getById(long ID);
    void update(Book book);
    void delete(long ID);
    void delete(Book value);
}
