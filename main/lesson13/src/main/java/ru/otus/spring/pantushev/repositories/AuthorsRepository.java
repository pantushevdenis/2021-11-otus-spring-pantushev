package ru.otus.spring.pantushev.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.otus.spring.pantushev.domain.Author;

import java.util.List;

public interface AuthorsRepository
    extends MongoRepository<Author, ObjectId> {

    List<Author> findAuthorsByUserIdIsEndingWith(String str);

    @Query(
        value = "{}",
        fields = "{shortName : 1}"
    )
    List<ru.otus.spring.pantushev.domain.dropdownLists.Author> findAllList();

    @Query(
        fields = "{fullName : 1}"
    )
    List<ru.otus.spring.pantushev.domain.documents.bookDocument.Author> findAuthorsDocItemByUserIdIsEndingWith(String str);

}
