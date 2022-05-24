package ru.otus.spring.pantushev.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.pantushev.domain.Author;
import ru.otus.spring.pantushev.domain.Book;
import ru.otus.spring.pantushev.dto.AuthorDto;
import ru.otus.spring.pantushev.dto.BookDto;
import ru.otus.spring.pantushev.repositories.AuthorsRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController
public class AuthorsRestController {
    private AuthorsRepository authorsRepository;

    @Autowired
    public AuthorsRestController(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    @GetMapping("/authors")
    public List<Author> getAuthors() {
        List<Author> authors = authorsRepository.findAll();
        return authors;
    }

    @GetMapping("/dropdowns/authors")
        public List<AuthorDto> getDropdownAuthors() {
        List<Author> authors = authorsRepository.findAll();
        List<AuthorDto> authorDto = authors.stream().map(o -> new AuthorDto(o)).collect(Collectors.toList());
        return authorDto;
    }



}
