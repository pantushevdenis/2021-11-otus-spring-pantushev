package ru.otus.spring.pantushev.services.printTable;

import ru.otus.spring.pantushev.domain.Printable;

import java.util.List;

public interface PrintTableService<T extends Printable> {
    void printTable(Iterable<T> table, Class<? extends Printable> elementClass);
}
