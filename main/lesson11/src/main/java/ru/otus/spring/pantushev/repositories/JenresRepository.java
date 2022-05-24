package ru.otus.spring.pantushev.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.pantushev.domain.Jenre;

public interface JenresRepository
    extends CrudRepository<Jenre, Long> {
}
