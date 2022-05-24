package ru.otus.spring.pantushev.controllers.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BooksController {
    @GetMapping("/books")
    public String listPage(Model model) {
        return "books";
    }

    @GetMapping("/books/add")
    public String add(Model model) {
        model.addAttribute("isEdit", Boolean.FALSE);
        model.addAttribute("id", 0L);
        return "bookEdit";
    }

    @GetMapping("/books/edit/{id}")
    public String getEdit(@PathVariable("id") String id, Model model) {
        model.addAttribute("isEdit", Boolean.TRUE);
        model.addAttribute("id", id);
        return "bookEdit";
    }
}

