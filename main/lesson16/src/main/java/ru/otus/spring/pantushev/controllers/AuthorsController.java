package ru.otus.spring.pantushev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring.pantushev.domain.Author;
import ru.otus.spring.pantushev.domain.Jenre;
import ru.otus.spring.pantushev.repositories.AuthorsRepository;

import java.util.List;

@Controller
public class AuthorsController {
    private final AuthorsRepository authorsRepository;

    @Autowired
    public AuthorsController(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    @GetMapping("/authors")
    public String listPage(Model model) {
        List<Author> authors = authorsRepository.findAll();
        model.addAttribute("authors", authors);
        return "authors";
    }

}
