package ru.otus.spring.pantushev.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.pantushev.domain.mongodb.Author;
import ru.otus.spring.pantushev.domain.mongodb.Jenre;

public interface JenresRepository
    extends MongoRepository<Jenre, String> {

    Jenre findJenreByFullName(String s);
    boolean existsJenreByFullName(String s);

}
