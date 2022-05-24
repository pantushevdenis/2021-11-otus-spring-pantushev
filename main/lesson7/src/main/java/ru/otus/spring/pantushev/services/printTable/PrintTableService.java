package ru.otus.spring.pantushev.services.printTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.pantushev.domain.Printable;
import ru.otus.spring.pantushev.services.IOServiceSys;

import java.util.List;

public interface PrintTableService<T extends Printable> {
    void printTable(List<T> table, Class<? extends Printable> elementClass);
}
