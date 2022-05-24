package ru.otus.spring.pantushev.controllers.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthorsController {

    @GetMapping("/authors")
    public String listPage(Model model) {
        return "authors";
    }

}
