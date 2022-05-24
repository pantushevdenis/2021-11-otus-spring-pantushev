package ru.otus.spring.pantushev.services.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.pantushev.dao.*;
import ru.otus.spring.pantushev.domain.Author;
import ru.otus.spring.pantushev.domain.Book;
import ru.otus.spring.pantushev.domain.Jenre;
import ru.otus.spring.pantushev.domain.views.BookViewAll;

import java.util.List;

@Service
public class CommonServiceImpl
    implements CommonService
{
    private final AuthorsDao authorsDao;
    private final JenresDao jenresDao;
    private final BooksDao booksDao;

    @Autowired
    public CommonServiceImpl(AuthorsDaoJdbc authorsDaoJdbc, JenresDaoJdbc jenresDaoJdbc, BooksDaoJdbc booksDaoJdbc) {
        this.authorsDao = authorsDaoJdbc;
        this.jenresDao = jenresDaoJdbc;
        this.booksDao = booksDaoJdbc;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAuthorAll() {
        return  authorsDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Jenre> getJenreAll() {
        return jenresDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getBookAll() {
        return booksDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public int getBookCount() {
        return booksDao.getCount();
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBookById(long id) {
        return booksDao.getById(id);
    }

    @Override
    @Transactional
    public Book insertBook(Book book) {
        return booksDao.insert(book);
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        booksDao.update(book);
    }

    @Override
    @Transactional
    public void updateBook(long id, String name, Long authorId, Long jenreId, Integer publishingYear) {
        Book b = null;
        if (name == null || authorId == null || jenreId == null || publishingYear == null) {
            b = booksDao.getById(id);
            if (b != null) {
                if (name != null) {
                    b.setName(name);
                }
                if (authorId != null) {
                    b.setAuthorID(authorId);
                }
                if (jenreId != null) {
                    b.setJenreID(jenreId);
                }
                if(publishingYear!= null) {
                    b.setPublishingYear(publishingYear);
                }
            }
        }
        else {
            b = new Book(id, name, authorId, jenreId, publishingYear);
        }
        booksDao.update(b);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookViewAll> getBookViewAll() {
        return booksDao.getAllBookViewAll();
    }
}
