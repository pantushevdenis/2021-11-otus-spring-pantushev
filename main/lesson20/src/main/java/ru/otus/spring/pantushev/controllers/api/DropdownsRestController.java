package ru.otus.spring.pantushev.controllers.api;

import reactor.core.publisher.Flux;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.pantushev.dto.dropdownLists.AuthorDto;
import ru.otus.spring.pantushev.dto.dropdownLists.JenreDto;
import ru.otus.spring.pantushev.repositories.AuthorsRepository;
import ru.otus.spring.pantushev.repositories.JenresRepository;

import java.util.List;

@RequestMapping("/api/dropdowns")
@RestController
public class DropdownsRestController {
    private final AuthorsRepository authorsRepository;
    private final JenresRepository jenresRepository;


    public DropdownsRestController(AuthorsRepository authorsRepository, JenresRepository jenresRepository) {
        this.authorsRepository = authorsRepository;
        this.jenresRepository = jenresRepository;
    }


    @GetMapping("/jenres")
    public Flux<JenreDto> getDropdownJenres() {
        Flux<JenreDto> jenres = jenresRepository.findAllDropdownList();
        return jenres;
    }

    @GetMapping("/authors")
    public Flux<AuthorDto> getDropdownAuthors() {
        Flux<AuthorDto> authors = authorsRepository.findAllDropdownList();
        return authors;
    }

}
