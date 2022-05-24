package ru.otus.spring.pantushev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring.pantushev.domain.Author;
import ru.otus.spring.pantushev.dto.AuthenticationCommonPageDataDto;
import ru.otus.spring.pantushev.repositories.AuthorsRepository;
import ru.otus.spring.pantushev.security.AuthenticationUtilsService;

import java.util.List;

@Controller
public class AuthorsController {
    private final AuthorsRepository authorsRepository;
    private final AuthenticationUtilsService authenticationService;

    @Autowired
    public AuthorsController(AuthorsRepository authorsRepository, AuthenticationUtilsService authenticationService) {
        this.authorsRepository = authorsRepository;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/authors")
    public String listPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticationCommonPageDataDto authenticationCommonPageDataDto = authenticationService.getAuthenticationCommonPageDataDto(authentication);
        model.addAttribute("authenticationCommonPageDataDto", authenticationCommonPageDataDto);

        List<Author> authors = authorsRepository.findAll();
        model.addAttribute("authors", authors);
        return "authors";
    }

}
