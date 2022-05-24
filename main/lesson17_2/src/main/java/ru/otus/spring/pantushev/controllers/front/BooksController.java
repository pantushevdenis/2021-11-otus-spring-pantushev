package ru.otus.spring.pantushev.controllers.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.pantushev.controllers.api.NotFoundException;
import ru.otus.spring.pantushev.domain.Book;
import ru.otus.spring.pantushev.dto.BookDto;
import ru.otus.spring.pantushev.repositories.AuthorsRepository;
import ru.otus.spring.pantushev.repositories.BooksRepository;
import ru.otus.spring.pantushev.repositories.JenresRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BooksController {
    final private BooksRepository booksRepository;
    final private AuthorsRepository authorsRepository;
    final private JenresRepository jenresRepository;

    @Autowired
    public BooksController(BooksRepository booksRepository, AuthorsRepository authorsRepository, JenresRepository jenresRepository) {
        this.booksRepository = booksRepository;
        this.authorsRepository = authorsRepository;
        this.jenresRepository = jenresRepository;
    }

    @GetMapping("/books")
    public String listPage(Model model) {
        return "books";
    }

    @GetMapping("/books/add")
    public String add(Model model) {
        model.addAttribute("isEdit", new Boolean(false));
        model.addAttribute("id", 0L);
        return "bookEdit";
    }

    @GetMapping("/books/edit/{id}")
    public String getEdit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("isEdit", new Boolean(true));
        model.addAttribute("id", id);
        return "bookEdit";
    }
}

