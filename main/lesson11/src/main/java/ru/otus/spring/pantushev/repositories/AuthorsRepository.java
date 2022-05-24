package ru.otus.spring.pantushev.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.pantushev.domain.Author;

public interface AuthorsRepository
    extends CrudRepository<Author, Long> {
}
