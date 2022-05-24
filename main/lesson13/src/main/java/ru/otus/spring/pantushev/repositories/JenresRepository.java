package ru.otus.spring.pantushev.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.otus.spring.pantushev.domain.Jenre;

import java.util.List;

public interface JenresRepository
    extends MongoRepository<Jenre, ObjectId> {

    List<Jenre> findByUserIdIsEndingWith(String str);

    @Query(
        value = "{}",
        fields = "{shortName : 1}"
    )
    List<ru.otus.spring.pantushev.domain.dropdownLists.Jenre> findAllList();

    @Query(
        fields = "{fullName : 1}"
    )
    List<ru.otus.spring.pantushev.domain.documents.bookDocument.Jenre> findJenresDocItemByUserIdIsEndingWith(String str);


}
