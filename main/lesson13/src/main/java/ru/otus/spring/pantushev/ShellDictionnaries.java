package ru.otus.spring.pantushev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.pantushev.domain.Author;
import ru.otus.spring.pantushev.domain.Jenre;
import ru.otus.spring.pantushev.services.IOServiceSys;
import ru.otus.spring.pantushev.services.data.DictionnariesServiceImpl;
import ru.otus.spring.pantushev.services.printTable.PrintTableServiceSys;

@ShellComponent
public class ShellDictionnaries {
    final IOServiceSys ios;
    final PrintTableServiceSys pts;
    final DictionnariesServiceImpl ds;

    @Autowired
    public ShellDictionnaries(IOServiceSys ioServiceSys, PrintTableServiceSys printTableServiceSys, DictionnariesServiceImpl dictionnariesService) {
        this.ios = ioServiceSys;
        this.pts = printTableServiceSys;
        this.ds = dictionnariesService;
    }

    @ShellMethod("show Authors table")
    public void showAuthors() {
        Iterable<Author> la = ds.findAuthorsAll();
        pts.printTable(la, Author.class);

    }

    @ShellMethod("find Author by id")
    public void showAuthorsById(String id) {
        Iterable<Author> la = ds.findAuthorsByUserIdIsEndingWith(id);
        pts.printTable(la, Author.class);

    }


    @ShellMethod("show Jenres table")
    public void showJenres() {
        Iterable<Jenre> la = ds.findJenreAll();
        pts.printTable(la, Jenre.class);
    }

    @ShellMethod("find Jenres by id")
    public void showJenresById(String id) {
        Iterable<Jenre> l = ds.findJenresByUserIdIsEndingWith(id);
        pts.printTable(l, Author.class);

    }

    @ShellMethod("Authors dropdownslist")
    public void showAuthorsList() {
        Iterable<ru.otus.spring.pantushev.domain.dropdownLists.Author> l = ds.findAllAuthorsList();
        pts.printTable(l, ru.otus.spring.pantushev.domain.dropdownLists.Author.class);
    }

    @ShellMethod("Jenres dropdownslist")
    public void showJenresList() {
        Iterable<ru.otus.spring.pantushev.domain.dropdownLists.Jenre> l = ds.findAllJenresList();
        pts.printTable(l, ru.otus.spring.pantushev.domain.dropdownLists.Author.class);
    }



}
