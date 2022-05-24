package ru.otus.spring.pantushev.services.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.pantushev.dao.*;
import ru.otus.spring.pantushev.domain.*;

import java.util.List;

@Service
public class CommonServiceImpl
    implements CommonService
{
    final AuthorsDao authorsDao;
    final JenresDao jenresDao;
    final BooksDao booksDao;

    @Autowired
    public CommonServiceImpl(AuthorsDaoJpa authorsDaoJpa, JenresDaoJpa jenresDaoJpa, BooksDaoJpa booksDaoJpa) {
        this.authorsDao = authorsDaoJpa;
        this.jenresDao = jenresDaoJpa;
        this.booksDao = booksDaoJpa;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAuthorAll() {
        return  authorsDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Author getAuthorById(long id) {
        return authorsDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Jenre> getJenreAll() {
        return jenresDao.getAll();
    }

    @Override
    public Jenre getJenreById(long id) {
        return jenresDao.getByid(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getBookAll() {
        return booksDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public long getBookCount() {
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
        Book b = booksDao.getById(id);
        if (b != null) {
            if (name != null) {
                b.setName(name);
            }
            if (authorId != null) {
                Author a = authorsDao.getById(authorId);
                b.setAuthor(a);
            }
            if (jenreId != null) {
                b.setJenre(jenresDao.getByid(jenreId));
            }
            if(publishingYear!= null) {
                b.setPublishingYear(publishingYear);
            }
        }
        booksDao.update(b);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getCommentsByBook(long bookId) {
        Book book = booksDao.getById(bookId);
        return book.getComments();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getCommentsByBook(Book book) {
        return book.getComments();
    }
}
