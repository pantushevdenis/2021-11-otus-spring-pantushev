package ru.otus.spring.pantushev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.pantushev.domain.Author;
import ru.otus.spring.pantushev.domain.Book;
import ru.otus.spring.pantushev.domain.Jenre;
import ru.otus.spring.pantushev.domain.views.BookViewAll;
import ru.otus.spring.pantushev.services.data.*;
import ru.otus.spring.pantushev.services.IOServiceSys;
import ru.otus.spring.pantushev.services.printTable.PrintTableServiceSys;

import java.util.Arrays;
import java.util.List;

@ShellComponent
public class ShellApplication {
    final IOServiceSys ios;
    final PrintTableServiceSys pts;
    final CommonServiceImpl commonDataService;

    @Autowired
    public ShellApplication(IOServiceSys ioServiceSys, PrintTableServiceSys pts, CommonServiceImpl commonDataService) {
        this.ios = ioServiceSys;
        this.pts = pts;
        this.commonDataService = commonDataService;
    }

    @ShellMethod("show Authors table")
    public void showAuthors() {
        List<Author> la = commonDataService.getAuthorAll();
        pts.printTable(la, Author.class);

    }

    @ShellMethod("show Jenres table")
    public void showJenres() {
        List<Jenre> la = commonDataService.getJenreAll();
        pts.printTable(la, Jenre.class);

    }

    @ShellMethod("show Books table all with dictionnaries")
    public void showBooksAll() {
        List<BookViewAll> la = commonDataService.getBookViewAll();
        pts.printTable(la, BookViewAll.class);
    }

    @ShellMethod("show Books table")
    public void showBooks() {
        List<Book> la = commonDataService.getBookAll();
        pts.printTable(la, Book.class);
    }

    @ShellMethod("show Books count")
    public void showBooksCount() {
        int count = commonDataService.getBookCount();
        ios.getOut().println("Count of Book table: " + count);
    }

    @ShellMethod("show Book")
    public void showBook(long id) {
        Book b = commonDataService.getBookById(id);
        List<Book> bl= Arrays.asList(b);
        pts.printTable(bl, Book.class);
    }

    @ShellMethod("insert book")
    public void insertBook(String name, long authorId, long jenreId, @ShellOption(defaultValue = ShellOption.NULL) Integer publishingYear) {
        Book b = new Book(name, authorId, jenreId, publishingYear);
        Book inserted = commonDataService.insertBook(b);
        ios.getOut().println("book inserted as ID: "+ inserted.getId());
    }

    @ShellMethod("update book")
    public void updateBook(
            long id,
            @ShellOption(defaultValue = ShellOption.NULL) String name,
            @ShellOption(defaultValue = ShellOption.NULL) Long authorId,
            @ShellOption(defaultValue = ShellOption.NULL) Long jenreId,
            @ShellOption(defaultValue = ShellOption.NULL) Integer publishingYear
    )
    {
        commonDataService.updateBook(id, name, authorId, jenreId, publishingYear);
        ios.getOut().println("book updated");
    }
}
