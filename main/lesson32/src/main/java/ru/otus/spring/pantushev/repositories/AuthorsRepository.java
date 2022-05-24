package ru.otus.spring.pantushev.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.pantushev.entities.Author;

import java.util.List;

public interface AuthorsRepository
    extends CrudRepository<Author, Long> {
    List<Author> findAll();
}
