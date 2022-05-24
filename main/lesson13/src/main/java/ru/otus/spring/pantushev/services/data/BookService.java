package ru.otus.spring.pantushev.services.data;

import org.bson.types.ObjectId;
import ru.otus.spring.pantushev.domain.documents.bookDocument.Book;
import ru.otus.spring.pantushev.domain.documents.bookDocument.Comment;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Iterable<Book> getBookAll();
    long getBookCount();
    Optional<Book> getBookById(ObjectId id);
    Book saveBook(Book book);
    void updateBook(
            ObjectId id,
            String name,
            ObjectId authorId,
            ObjectId jenreId,
            Integer publishingYear
    ) throws Exception;

    Iterable<Book> findBooksByJenreLike(String name);
    Iterable<Book> findBooksByAuthorLike(String name);

    List<Comment> getCommentsByBook(ObjectId bookId) throws Exception;
    List<Comment> getCommentsByBook(Book book);


    Book insertBook(String name, String authorIdSubstr, String jenreIdSubstr, Integer publishingYear) throws Exception;
    void deleteBook(ObjectId id);

    void addComment(ObjectId id, Comment comment) throws Exception;

}
