package ru.otus.spring.pantushev.services.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.pantushev.domain.Author;
import ru.otus.spring.pantushev.domain.Book;
import ru.otus.spring.pantushev.domain.Comment;
import ru.otus.spring.pantushev.domain.Jenre;
import ru.otus.spring.pantushev.repositories.AuthorsRepository;
import ru.otus.spring.pantushev.repositories.BooksRepository;
import ru.otus.spring.pantushev.repositories.JenresRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommonServiceImpl
    implements CommonService
{
    private final AuthorsRepository authorsRepository;
    private final JenresRepository jenresRepository;
    private final BooksRepository booksRepository;

    @Autowired
    public CommonServiceImpl(AuthorsRepository authorsRepository, JenresRepository jenresRepository, BooksRepository booksRepository) {
        this.authorsRepository = authorsRepository;
        this.jenresRepository = jenresRepository;
        this.booksRepository = booksRepository;
    }





    @Override
    @Transactional(readOnly = true)
    public Iterable<Book> getBookAll() {
        return booksRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public long getBookCount() {
        return booksRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> getBookById(long id) {
        return booksRepository.findById(id);
    }

    @Override
    @Transactional
    public Book saveBook(Book book) {
        return booksRepository.save(book);
    }

    @Override
    @Transactional
    public void updateBook(long id, String name, Long authorId, Long jenreId, Integer publishingYear) {
        Book b = booksRepository.findById(id).orElse(null);
        if (b != null) {
            if (name != null) {
                b.setName(name);
            }
            if (authorId != null) {
                Author a = authorsRepository.findById(authorId).orElseThrow();
                b.setAuthor(a);
            }
            if (jenreId != null) {
                Jenre j = jenresRepository.findById(jenreId).orElseThrow();
                b.setJenre(j);
            }
            if(publishingYear!= null) {
                b.setPublishingYear(publishingYear);
            }
        }
        booksRepository.save(b);
    }
    @Override
    @Transactional(readOnly = true)
    public Iterable<Book> findBooksByJenreLike(String name) {
        return booksRepository.findBooksByJenre_NameLike(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Book> findBooksByAuthorLike(String name) {
        return booksRepository.findBooksByAuthor_NameLike(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getCommentsByBook(long bookId) {
        Book book = booksRepository.findById(bookId).get();
        return book.getComments();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getCommentsByBook(Book book) {
        return book.getComments();
    }


}
