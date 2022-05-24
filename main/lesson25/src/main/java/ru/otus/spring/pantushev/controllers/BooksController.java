package ru.otus.spring.pantushev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.pantushev.entities.Book;
import ru.otus.spring.pantushev.dto.*;
import ru.otus.spring.pantushev.exceptions.NotFoundException;
import ru.otus.spring.pantushev.properties.DebugSettings;
import ru.otus.spring.pantushev.repositories.AuthorsRepository;
import ru.otus.spring.pantushev.repositories.BooksRepository;
import ru.otus.spring.pantushev.repositories.JenresRepository;
import ru.otus.spring.pantushev.security.AuthenticationUtilsService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
public class BooksController {
    public static final String BOOK_SORTED = "bookSorted";

    private BooksRepository booksRepository;
    private AuthorsRepository authorsRepository;
    private JenresRepository jenresRepository;
    private AuthenticationUtilsService authenticationService;
    private DebugSettings debugSettings;

    @Autowired
    public BooksController(BooksRepository booksRepository, AuthorsRepository authorsRepository, JenresRepository jenresRepository, AuthenticationUtilsService authenticationService, DebugSettings debugSettings) {
        this.booksRepository = booksRepository;
        this.authorsRepository = authorsRepository;
        this.jenresRepository = jenresRepository;
        this.authenticationService = authenticationService;
        this.debugSettings = debugSettings;
    }

    private DebugShowInfoDto filldebugShowDto(HttpSession httpSession) {
        DebugShowInfoDto res = new DebugShowInfoDto();

        res.setJSessionId(httpSession.getId());
        return res;
    }

    @GetMapping("/books")
    public String listPage(Model model, HttpSession httpSession) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean showInfo = debugSettings.isShowInfo();
        model.addAttribute("showInfo", debugSettings.isShowInfo());
        if (showInfo) {
            DebugShowInfoDto debugShowInfo = filldebugShowDto(httpSession);
            model.addAttribute("debugShowInfo", debugShowInfo);
        }

        AuthenticationCommonPageDataDto authenticationCommonPageDataDto = authenticationService.toAuthenticationCommonPageDataDto(authentication);
        model.addAttribute("authenticationCommonPageDataDto", authenticationCommonPageDataDto);

        List<Book> books = booksRepository.findAll();

        SortedDto sortedDto = Optional.ofNullable((SortedDto) httpSession.getAttribute(BOOK_SORTED))
            .orElse(new SortedDto());
        model.addAttribute("sorted", sortedDto);
        if (sortedDto.getId() != 0) {
            Comparator<Book> comparator;
            switch (sortedDto.getId()) {
                case (1):
                    comparator = Comparator.comparing(Book::getName);
                    break;
                case(2):
                    comparator = Comparator.comparing(b -> b.getAuthor().getName());
                    break;
                case(3):
                    comparator = Comparator.comparing(b -> b.getJenre().getName());
                    break;
                case(4):
                    comparator = Comparator.comparing(Book::getPublishingYear);
                    break;
                default:
                    comparator = Comparator.comparingLong(Book::getId);
            }
            books.sort(comparator);
        }

        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/books/add")
    public String add(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticationCommonPageDataDto authenticationCommonPageDataDto = authenticationService.toAuthenticationCommonPageDataDto(authentication);
        model.addAttribute("authenticationCommonPageDataDto", authenticationCommonPageDataDto);

        model.addAttribute("isEdit", Boolean.FALSE);
        BookDto bookDto = new BookDto();
        model.addAttribute("book", bookDto);
        model.addAttribute("authorsList", authorsRepository.findAll());
        model.addAttribute("jenresList", jenresRepository.findAll());

        return "bookEdit";
    }

    @GetMapping("/books/edit")
    public String getEdit(@RequestParam("id") Long id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticationCommonPageDataDto authenticationCommonPageDataDto = authenticationService.toAuthenticationCommonPageDataDto(authentication);
        model.addAttribute("authenticationCommonPageDataDto", authenticationCommonPageDataDto);

        model.addAttribute("isEdit", Boolean.TRUE);
        Book bookObject = booksRepository.findById(id).orElseThrow(NotFoundException::new);
        BookDto bookDto = BookTransform.toDto(bookObject);
        model.addAttribute("book", bookDto);
        model.addAttribute("authorsList", authorsRepository.findAll());
        model.addAttribute("jenresList", jenresRepository.findAll());

        return "bookEdit";
    }

    @PostMapping("/books/setSorted")
    public String postSetSorted(
        @ModelAttribute("sorted") SortedDto sortedDto,
        Model model,
        HttpSession httpSession
    ) {
        httpSession.setAttribute(BOOK_SORTED, sortedDto);

        return "redirect:/books";
    }

    @Validated
    @PostMapping("/books/save")
    public String postEdit(
        @ModelAttribute("isEdit") Boolean isEdit,
        @Valid @ModelAttribute("book") BookDto bookDto,
        BindingResult bindingResult,
        Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticationCommonPageDataDto authenticationCommonPageDataDto = authenticationService.toAuthenticationCommonPageDataDto(authentication);
        model.addAttribute("authenticationCommonPageDataDto", authenticationCommonPageDataDto);

        if (bindingResult.hasErrors()) {
            model.addAttribute("isEdit", isEdit);
            model.addAttribute("book", bookDto);
            model.addAttribute("authorsList", authorsRepository.findAll());
            model.addAttribute("jenresList", jenresRepository.findAll());

            return "bookEdit";
        }
        booksRepository.save(BookTransform.toEntity(bookDto));
        return "redirect:/books";
    }

    @GetMapping("/books/delete")
    public String getDelete(
        @RequestParam("id") Long id,
        Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticationCommonPageDataDto authenticationCommonPageDataDto = authenticationService.toAuthenticationCommonPageDataDto(authentication);
        model.addAttribute("authenticationCommonPageDataDto", authenticationCommonPageDataDto);

        Book bookObject = booksRepository.findById(id).orElseThrow(NotFoundException::new);
        BookDto bookDto = BookTransform.toDto(bookObject);
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

