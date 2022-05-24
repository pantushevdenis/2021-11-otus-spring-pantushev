package ru.otus.spring.pantushev.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.pantushev.domain.documents.bookDocument.Book;

import java.util.List;
import java.util.Optional;

public interface BooksRepository
    extends MongoRepository<Book, ObjectId> {

    List<Book> findBooksByJenre_FullNameLike(String name);
    List<Book> findBooksByAuthor_FullNameLike(String name);
}
