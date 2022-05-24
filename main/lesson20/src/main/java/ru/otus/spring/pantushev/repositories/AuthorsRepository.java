package ru.otus.spring.pantushev.repositories;

import reactor.core.publisher.Flux;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.otus.spring.pantushev.domain.Author;
import ru.otus.spring.pantushev.dto.dropdownLists.AuthorDto;


public interface AuthorsRepository
    extends ReactiveMongoRepository<Author, String> {

    @Query(
        value = "{}",
        fields = "{shortName : 1}"
    )
    Flux<AuthorDto> findAllDropdownList();
}
