package ru.otus.spring.pantushev.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import org.springframework.data.mongodb.repository.Query;
import ru.otus.spring.pantushev.domain.Jenre;
import ru.otus.spring.pantushev.dto.dropdownLists.JenreDto;


public interface JenresRepository
    extends ReactiveMongoRepository<Jenre, String> {

    @Query(
        value = "{}",
        fields = "{shortName : 1}"
    )
    Flux<JenreDto> findAllDropdownList();
}
