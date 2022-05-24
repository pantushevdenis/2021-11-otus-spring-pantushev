package ru.otus.spring.pantushev.services.data;

import org.bson.types.ObjectId;
import ru.otus.spring.pantushev.domain.Author;
import ru.otus.spring.pantushev.domain.Jenre;

import java.util.List;
import java.util.Optional;

public interface DictionnariesService {
    Optional<Author> findAuthorById(ObjectId id);
    List<Author> findAuthorsAll();
    List<Author> findAuthorsByUserIdIsEndingWith(String str);

    Optional<Jenre> findJenreById(ObjectId id);
    List<Jenre> findJenreAll();
    List<Jenre> findJenresByUserIdIsEndingWith(String str);

    List<ru.otus.spring.pantushev.domain.dropdownLists.Author> findAllAuthorsList();
    List<ru.otus.spring.pantushev.domain.dropdownLists.Jenre> findAllJenresList();

}
