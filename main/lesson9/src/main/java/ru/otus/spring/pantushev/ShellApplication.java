package ru.otus.spring.pantushev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.pantushev.domain.Author;
import ru.otus.spring.pantushev.domain.Book;
import ru.otus.spring.pantushev.domain.Comment;
import ru.otus.spring.pantushev.domain.Jenre;
import ru.otus.spring.pantushev.services.IOServiceSys;
import ru.otus.spring.pantushev.services.data.CommonServiceImpl;
import ru.otus.spring.pantushev.services.printTable.PrintTableServiceSys;

import java.util.Arrays;
import java.util.List;

@ShellComponent
public class ShellApplication {
    final IOServiceSys ios;
    final PrintTableServiceSys pts;
    final CommonServiceImpl cds;


    @Autowired
    public ShellApplication(IOServiceSys ioServiceSys, PrintTableServiceSys pts, CommonServiceImpl cds) {
        this.ios = ioServiceSys;
        this.pts = pts;
        this.cds = cds;
    }

    @ShellMethod("show Authors table")
    public void showAuthors() {
        List<Author> la = cds.getAuthorAll();
        pts.printTable(la, Author.class);

    }

    @ShellMethod("show Jenres table")
    public void showJenres() {
        List<Jenre> la = cds.getJenreAll();
        pts.printTable(la, Jenre.class);

    }

    @ShellMethod("show Books count")
    public void showBooksCount() {
        long count = cds.getBookCount();
        ios.out.println("Count of Book table: " + count);
    }

    @ShellMethod("show Books table")
    public void showBooks() {
        List<Book> la = cds.getBookAll();
        pts.printTable(la, Book.class);
    }


    @ShellMethod("show Book by id")
    public void showBook(long id) {
        Book b = cds.getBookById(id);
        List<Book> bl= Arrays.asList(b);
        pts.printTable(bl, Book.class);
    }


    @ShellMethod("insert book")
    public void insertBook(String name, long authorId, long jenreId, @ShellOption(defaultValue = ShellOption.NULL) Integer publishingYear) {
        Book b = Book.builder(name, cds.getAuthorById(authorId), cds.getJenreById(jenreId))
            .publishingYear(publishingYear)
            .build();
        Book inserted = cds.insertBook(b);
        ios.out.println("book inserted as ID: "+ inserted.getId());
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
        cds.updateBook(id, name, authorId, jenreId, publishingYear);
        ios.out.println("book updated");
    }

    @ShellMethod("get comments by book")
    public void getCommentsByBook(long id) {
        Iterable<Comment> comments = cds.getCommentsByBook(id);
        pts.printTable(comments, Comment.class);

    }
}
