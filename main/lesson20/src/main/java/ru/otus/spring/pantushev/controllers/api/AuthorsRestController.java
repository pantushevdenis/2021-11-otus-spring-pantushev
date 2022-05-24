package ru.otus.spring.pantushev.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.spring.pantushev.domain.Author;
import ru.otus.spring.pantushev.repositories.AuthorsRepository;

@RequestMapping("/api")
@RestController
public class AuthorsRestController {
    private final AuthorsRepository authorsRepository;


    @Autowired
    public AuthorsRestController(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }


    @GetMapping("/authors")
    public Flux<Author> getAuthors() {
        Flux<Author> authors = authorsRepository.findAll();
        return authors;
    }
}
