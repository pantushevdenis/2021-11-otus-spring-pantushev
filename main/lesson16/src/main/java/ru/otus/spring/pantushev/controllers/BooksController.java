package ru.otus.spring.pantushev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.pantushev.domain.Book;
import ru.otus.spring.pantushev.dto.BookDto;
import ru.otus.spring.pantushev.repositories.AuthorsRepository;
import ru.otus.spring.pantushev.repositories.BooksRepository;
import ru.otus.spring.pantushev.repositories.JenresRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BooksController {
    private final BooksRepository booksRepository;
    private final AuthorsRepository authorsRepository;
    private final JenresRepository jenresRepository;

    @Autowired
    public BooksController(BooksRepository booksRepository, AuthorsRepository authorsRepository, JenresRepository jenresRepository) {
        this.booksRepository = booksRepository;
        this.authorsRepository = authorsRepository;
        this.jenresRepository = jenresRepository;
    }

    @GetMapping("/books")
    public String listPage(Model model) {
        List<Book> books = booksRepository.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/books/add")
    public String add(Model model) {
        model.addAttribute("isEdit", new Boolean(false));
        BookDto bookDto = new BookDto();
        model.addAttribute("book", bookDto);
        model.addAttribute("authorsList", authorsRepository.findAll());
        model.addAttribute("jenresList", jenresRepository.findAll());

        return "bookEdit";
    }

    @GetMapping("/books/edit")
    public String getEdit(@RequestParam("id") Long id, Model model) {
        model.addAttribute("isEdit", new Boolean(true));
        Book bookObject = booksRepository.findById(id).orElseThrow(NotFoundException::new);
        BookDto bookDto = new BookDto(bookObject);
        model.addAttribute("book", bookDto);
        model.addAttribute("authorsList", authorsRepository.findAll());
        model.addAttribute("jenresList", jenresRepository.findAll());

        return "bookEdit";
    }

    @Validated
    @PostMapping("/books/save")
    public String postEdit(
        @ModelAttribute("isEdit") Boolean isEdit,
        @Valid @ModelAttribute("book") BookDto bookDto,
        BindingResult bindingResult,
        Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("isEdit", isEdit);
            model.addAttribute("book", bookDto);
            model.addAttribute("authorsList", authorsRepository.findAll());
            model.addAttribute("jenresList", jenresRepository.findAll());

            return "bookEdit";
        }
        booksRepository.save(bookDto.toDomain());
        return "redirect:/books";
    }

    @GetMapping("/books/delete")
    public String getDelete(
        @RequestParam("id") Long id,
        Model model
    ) {
        Book bookObject = booksRepository.findById(id).orElseThrow(NotFoundException::new);
        BookDto bookDto = new BookDto(bookObject);
        model.addAttribute("id", id);
        model.addAttribute("book", bookDto);
        return "bookDelete";
    }

    @PostMapping("/books/delete")
    public String postDelete(
        @ModelAttribute("id") Long id,
        Model model
    ) {
        booksRepository.deleteById(id);
        return "redirect:/books";
    }

}

