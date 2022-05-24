package ru.otus.spring.pantushev.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.pantushev.domain.mongodb.Author;

public interface AuthorsRepository
    extends MongoRepository<Author, String> {

    Author findAuthorByFullName(String s);
    boolean existsAuthorByFullName(String s);
}
