package ru.otus.spring.pantushev.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.pantushev.domain.mongodb.Author;
import ru.otus.spring.pantushev.domain.mongodb.Jenre;
import ru.otus.spring.pantushev.domain.mongodb.bookDocument.Book;
import ru.otus.spring.pantushev.repositories.AuthorsRepository;
import ru.otus.spring.pantushev.repositories.BooksRepository;
import ru.otus.spring.pantushev.repositories.JenresRepository;
import ru.otus.spring.pantushev.services.PrintCollectionServiceImpl;

import java.util.List;

@ShellComponent
public class ShellUtils {
    private final AuthorsRepository authorsRepository;
    private final JenresRepository jenresRepository;
    private final BooksRepository booksRepository;
    private final PrintCollectionServiceImpl printCollectionService;


    public ShellUtils(AuthorsRepository authorsRepository, JenresRepository jenresRepository, BooksRepository booksRepository, PrintCollectionServiceImpl printCollectionService) {
        this.authorsRepository = authorsRepository;
        this.jenresRepository = jenresRepository;
        this.booksRepository = booksRepository;
        this.printCollectionService = printCollectionService;
    }

    @ShellMethod("show Authors")
    public void showAuthors() {
        System.out.println();
        List<Author> l = authorsRepository.findAll();
        printCollectionService.printCollection(l, "Authors");
    }

    @ShellMethod("show Jenres")
    public void showJenres() {
        System.out.println();
        List<Jenre> l = jenresRepository.findAll();
        printCollectionService.printCollection(l, "Jenres");
    }

    @ShellMethod("show Books")
    public void showBooks() {
        System.out.println();
        List<Book> l = booksRepository.findAll();
        printCollectionService.printCollection(l, "Books");
    }

    @ShellMethod("clear Authors")
    public void clearAuthors() {
        System.out.println("clearing Authors");
        authorsRepository.deleteAll();
        System.out.println("clearing Authors done!");
    }

    @ShellMethod("clear Jenres")
    public void clearJenres() {
        System.out.println("clearing Jenres");
        jenresRepository.deleteAll();
        System.out.println("clearing Jenres done!");
    }

    @ShellMethod("clear Books")
    public void clearBooks() {
        System.out.println("clearing Books");
        booksRepository.deleteAll();
        System.out.println("clearing Books done!");
    }



}
