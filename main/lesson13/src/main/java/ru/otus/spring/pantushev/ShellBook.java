package ru.otus.spring.pantushev;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.pantushev.domain.documents.bookDocument.Book;
import ru.otus.spring.pantushev.domain.documents.bookDocument.Comment;
import ru.otus.spring.pantushev.services.IOServiceSys;
import ru.otus.spring.pantushev.services.data.BookServiceImpl;
import ru.otus.spring.pantushev.services.printTable.PrintTableServiceSys;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ShellComponent
public class ShellBook {


    final IOServiceSys ios;
    final PrintTableServiceSys pts;
    final BookServiceImpl cds;

    @Autowired
    public ShellBook(IOServiceSys ioServiceSys, PrintTableServiceSys pts, BookServiceImpl cds) {
        this.ios = ioServiceSys;
        this.pts = pts;
        this.cds = cds;
    }


    @ShellMethod("show Books count")
    public void showBooksCount() {
        long count = cds.getBookCount();
        ios.out.println("Count of Book table: " + count);
    }

    @ShellMethod("show Books table")
    public void showBooks() {
        Iterable<Book> la = cds.getBookAll();
        pts.printTable(la, Book.class);
    }

    @ShellMethod("show Book by id")
    public void showBook(String id) {
        ObjectId oid = new ObjectId(id);
        Optional<Book> b = cds.getBookById(oid);
        List<Book> bl= Arrays.asList(b.orElse(null));
        pts.printTable(bl, Book.class);
    }

    /*
        В качестве authorIdSubstr и jenreIdSubstr
        пользователь вводит последние несколько символов ObjectId,
        система ищет соответствующие объекты и вставляет их в книгу.
        Если соответствующих объектов больше 1 или не найдено, выводится ошибка.
     */
    @ShellMethod("insert book")
    public void insertBook(String name, String authorIdSubstr, String jenreIdSubstr, @ShellOption(defaultValue = ShellOption.NULL) Integer publishingYear) {
        try {
            Book inserted = cds.insertBook(name, authorIdSubstr, jenreIdSubstr, publishingYear);
            ios.out.println("book inserted as ID: "+ inserted.getId() + "\n");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @ShellMethod("delete book")
    public void deleteBook(String bookId) {

        System.out.println("book " + bookId + " deleted");
    }

    @ShellMethod("update book")
    public void updateBook(
            String id,
            @ShellOption(defaultValue = ShellOption.NULL) String name,
            @ShellOption(defaultValue = ShellOption.NULL) String authorId,
            @ShellOption(defaultValue = ShellOption.NULL) String jenreId,
            @ShellOption(defaultValue = ShellOption.NULL) Integer publishingYear
    ) {
        ObjectId oId = new ObjectId(id);
        ObjectId oAuthorId =  authorId != null ? new ObjectId(authorId) : null;
        ObjectId oJenreId = jenreId != null ? new ObjectId(jenreId) : null;
        try {
            cds.updateBook(oId, name, oAuthorId, oJenreId, publishingYear);
            ios.out.println("book updated");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    @ShellMethod("add comment")
    public void addComment(
            String id,
            String content
    ) {
        ObjectId aId = new ObjectId(id);
        Comment c = new Comment(new ObjectId(), content);
        try {
            cds.addComment(aId, c);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    @ShellMethod("show comments")
    public void showComments(String id) {
        try {
            List<Comment> commentLIst = cds.getCommentsByBook(new ObjectId(id));
            pts.printTable(commentLIst, Comment.class);
        }
            catch (Exception e) {
            System.out.println(e);
        }
    }
}
