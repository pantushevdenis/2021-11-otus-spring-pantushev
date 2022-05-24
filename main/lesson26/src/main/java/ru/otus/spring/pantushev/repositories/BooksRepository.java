package ru.otus.spring.pantushev.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.pantushev.domain.mongodb.bookDocument.Book;

public interface BooksRepository
    extends MongoRepository<Book, String> {

    Book findBookByName(String s);
    boolean existsBookByName(String s);

}
