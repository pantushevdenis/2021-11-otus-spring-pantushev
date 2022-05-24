package ru.otus.spring.pantushev.services.data;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import ru.otus.spring.pantushev.domain.Author;
import ru.otus.spring.pantushev.domain.documents.bookDocument.Book;
import ru.otus.spring.pantushev.domain.documents.bookDocument.Comment;
import ru.otus.spring.pantushev.domain.Jenre;
import ru.otus.spring.pantushev.repositories.AuthorsRepository;
import ru.otus.spring.pantushev.repositories.BooksRepository;
import ru.otus.spring.pantushev.repositories.JenresRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl
    implements BookService
{
    private final AuthorsRepository authorsRepository;
    private final JenresRepository jenresRepository;
    private final BooksRepository booksRepository;
    private final MongoTemplate mongoTemplate;


    @Autowired
    public BookServiceImpl(AuthorsRepository authorsRepository, JenresRepository jenresRepository, AuthorsRepository authorsRepository1, JenresRepository jenresRepository1, BooksRepository booksRepository, MongoTemplate mongoTemplate) {
        this.authorsRepository = authorsRepository1;
        this.jenresRepository = jenresRepository1;
        this.booksRepository = booksRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Book> getBookAll() {
        return booksRepository.findAll();
    }

    @Override
    public long getBookCount() {
        return booksRepository.count();
    }

    @Override
    public Optional<Book> getBookById(ObjectId id) {
        return booksRepository.findById(id);
    }

    @Override
    public Book saveBook(Book book) {
        return booksRepository.save(book);
    }

    @Override
    public void updateBook(ObjectId id, String name, ObjectId authorId, ObjectId jenreId, Integer publishingYear) throws Exception {
        Book b = booksRepository.findById(id)
                .orElseThrow(
                    () -> new Exception("Не найдена Книга с идентификатором " + id.toString())
                );

        if (name != null) {
            b.setName(name);
        }
        if (authorId != null) {
            Author a = authorsRepository.findById(authorId)
                    .orElseThrow(
                            () -> new Exception("Не найден Автор с идентификатором " + authorId.toString())
                    );
            ru.otus.spring.pantushev.domain.documents.bookDocument.Author adi = new ru.otus.spring.pantushev.domain.documents.bookDocument.Author(a);
            b.setAuthor(adi);
        }
        if (jenreId != null) {
            Jenre j = jenresRepository.findById(jenreId)
                    .orElseThrow(
                            () -> new Exception("Не найден Жанр с идентификатором " + jenreId.toString())
                    );
            ru.otus.spring.pantushev.domain.documents.bookDocument.Jenre jdi = new ru.otus.spring.pantushev.domain.documents.bookDocument.Jenre(j);
            b.setJenre(jdi);
        }
        if(publishingYear!= null) {
            b.setPublishingYear(publishingYear);
        }
        booksRepository.save(b);
    }


    @Override
    public List<Book> findBooksByJenreLike(String name) {
        return booksRepository.findBooksByJenre_FullNameLike(name);
    }

    @Override
    public Iterable<Book> findBooksByAuthorLike(String name) {
        return booksRepository.findBooksByAuthor_FullNameLike(name);
    }

    @Override
    public List<Comment> getCommentsByBook(ObjectId bookId) throws Exception {
        Book book = booksRepository.findById(bookId)
                .orElseThrow(
                        () -> new Exception("Не найдена Книга с идентификатором " + bookId.toString())
                );
        return book.getComments();
    }

    @Override
    public List<Comment> getCommentsByBook(Book book) {
        return book.getComments();
    }

    /*

     */
    @Override
    public Book insertBook(String name, String authorIdSubstr, String jenreIdSubstr, Integer publishingYear) throws Exception {
        List<ru.otus.spring.pantushev.domain.documents.bookDocument.Author> al = authorsRepository.findAuthorsDocItemByUserIdIsEndingWith(authorIdSubstr);
        if (al == null || al.isEmpty()) {
            throw new Exception("Не найден Автор с Id, заканчивающимся на " + authorIdSubstr);
        }
        if (al.size() > 1) {
            throw new Exception("Найдено слишком много Авторов с Id, заканчивающимся на " + authorIdSubstr + "\n" + al.toString());
        }

        List<ru.otus.spring.pantushev.domain.documents.bookDocument.Jenre> jl = jenresRepository.findJenresDocItemByUserIdIsEndingWith(jenreIdSubstr);
        if (jl == null || jl.isEmpty()) {
            throw new Exception("Не найден Жанр с Id, заканчивающимся на " + jenreIdSubstr);
        }
        if (jl.size() > 1) {
            throw new Exception("Найдено слишком много Жанров с Id, заканчивающимся на " + jenreIdSubstr +"\n" + jl.toString());
        }

        ru.otus.spring.pantushev.domain.documents.bookDocument.Author a = al.get(0);
        ru.otus.spring.pantushev.domain.documents.bookDocument.Jenre j = jl.get(0);

        Book b = new Book(name, a, j, publishingYear);
        Book inserted = booksRepository.save(b);

        return inserted;
    }

    @Override
    public void deleteBook(ObjectId id) {
        booksRepository.deleteById(id);
    }

    @Override
    public void addComment(ObjectId id, Comment comment) throws Exception {
        /*
        Query query = Query.query(
                Criteria.where("_id").is(id)
        );
        UpdateDefinition =
        mongoTemplate.updateFirst(query, )

         */

        Book b = booksRepository.findById(id)
                .orElseThrow(
                        () -> new Exception("Не найдена Книга с идентификатором " + id.toString())
                );
        b.getComments().add(comment);
        booksRepository.save(b);
    }

}
