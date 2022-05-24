package ru.otus.spring.pantushev.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import ru.otus.spring.pantushev.domain.bookDocument.Book;


public interface BooksRepository
    extends ReactiveMongoRepository<Book, String> {

    Flux<Book> findBooksByJenre_FullNameLike(String name);
    Flux<Book> findBooksByAuthor_FullNameLike(String name);
}
