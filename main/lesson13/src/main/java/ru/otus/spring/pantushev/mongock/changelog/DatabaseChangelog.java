package ru.otus.spring.pantushev.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.lang3.time.DateUtils;
import org.bson.types.ObjectId;
import ru.otus.spring.pantushev.domain.Author;
import ru.otus.spring.pantushev.domain.documents.bookDocument.Book;
import ru.otus.spring.pantushev.domain.Jenre;
import ru.otus.spring.pantushev.repositories.AuthorsRepository;
import ru.otus.spring.pantushev.repositories.BooksRepository;
import ru.otus.spring.pantushev.repositories.JenresRepository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ChangeLog
public class DatabaseChangelog {

    private List<Jenre> jenres = new ArrayList<>();
    private List<Author> authors = new ArrayList<>();
    private List<Book> books = new ArrayList<>();


    {
        try {
            jenres.add(new Jenre("Новелла", "Новелла"));
            jenres.add(new Jenre("Очерк", "Очерк"));
            jenres.add(new Jenre("Поэма", "Поэма"));
            jenres.add(new Jenre("Повесть", "Повесть"));
            jenres.add(new Jenre("Пьеса", "Пьеса"));
            jenres.add(new Jenre("Рассказ", "Рассказ"));

            authors.add(new Author("Алексей Николаевич Толстой", "Алексей Толстой", DateUtils.parseDate("10-01-1888", "dd-mm-yyyy")));
            authors.add(new Author("Эдуард Успенский", "Э Успенский", DateUtils.parseDate("2-12-1937", "dd-mm-yyyy")));
            authors.add(new Author("Лазарь Иосифович Лагин", "Л. Лагин", DateUtils.parseDate("21-11-1903", "dd-mm-yyyy")));
            authors.add(new Author("Виктор Юзефович Драгунский", "Виктор Драгунский", DateUtils.parseDate("01-12-1913", "dd-mm-yyyy")));
            authors.add(new Author("Лев Николаевич Толстой", "Лев Толстой", DateUtils.parseDate("28-08-1828", "dd-mm-yyyy")));

            books.add(new Book("Приключения Буратино",
                    new ru.otus.spring.pantushev.domain.documents.bookDocument.Author(authors.get(0)),
                    new ru.otus.spring.pantushev.domain.documents.bookDocument.Jenre(jenres.get(5)),
                    2011));
            books.add(new Book("Любимая девочка Дяди Федора",
                    new ru.otus.spring.pantushev.domain.documents.bookDocument.Author(authors.get(1)),
                    new ru.otus.spring.pantushev.domain.documents.bookDocument.Jenre(jenres.get(5)),
                    2000));
            books.add(new Book("Старик Хоттабыч",
                    new ru.otus.spring.pantushev.domain.documents.bookDocument.Author(authors.get(2)),
                    new ru.otus.spring.pantushev.domain.documents.bookDocument.Jenre(jenres.get(5)),
                    1990));
            books.add(new Book("Денискины рассказы",
                new ru.otus.spring.pantushev.domain.documents.bookDocument.Author(authors.get(3)),
                new ru.otus.spring.pantushev.domain.documents.bookDocument.Jenre(jenres.get(2)),
                2001));


        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @ChangeSet(order = "001", id = "dropCollections", author = "dpantyushev", runAlways = true)
    public void dropCollections(MongoDatabase db) {
        db.getCollection("author").drop();
        db.getCollection("jenre").drop();
        db.getCollection("book").drop();

    }

    @ChangeSet(order = "002", id = "insertAuthors", author = "dpantyushev", runAlways = true)
    public void insertAuthors(AuthorsRepository repository) {
        repository.saveAll(authors);
    }

    @ChangeSet(order = "003", id = "insertJenres", author = "dpantyushev", runAlways = true)
    public void insertJenres(JenresRepository repository) {
        repository.saveAll(jenres);
    }

    @ChangeSet(order = "004", id = "insertBooks", author = "dpantyushev", runAlways = true)
    public void insertBooks(BooksRepository repository) {
        repository.saveAll(books);
    }

    private <T> void outStrInsertedAs(T obj) {
        System.out.println("saved as " + obj.toString());
    }
}
