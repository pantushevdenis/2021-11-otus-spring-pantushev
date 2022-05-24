package ru.otus.spring.pantushev.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.pantushev.domain.Book;

import java.util.Optional;

public interface BooksRepository
    extends CrudRepository<Book, Long> {

    @EntityGraph(value = "Book.BookAndDict", type = EntityGraph.EntityGraphType.FETCH)
    Iterable<Book> findAll();

    @EntityGraph(value = "Book.BookAndDict", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Book> findById(Long id);

    @EntityGraph(value = "Book.BookAndDict", type = EntityGraph.EntityGraphType.FETCH)
    Iterable<Book> findBooksByJenre_NameLike(String name);

    @EntityGraph(value = "Book.BookAndDict", type = EntityGraph.EntityGraphType.FETCH)
    Iterable<Book> findBooksByAuthor_NameLike(String name);
}
