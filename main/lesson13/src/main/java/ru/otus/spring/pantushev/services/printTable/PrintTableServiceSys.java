package ru.otus.spring.pantushev.services.printTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.pantushev.domain.Printable;
import ru.otus.spring.pantushev.services.IOServiceSys;

import java.lang.reflect.Method;
import java.util.List;

@Service
public class PrintTableServiceSys<T extends Printable>
        implements PrintTableService<T>
{
    final IOServiceSys io;

    @Autowired
    public PrintTableServiceSys(IOServiceSys io) {
        this.io = io;
    }

    @Override
    public void printTable(Iterable<T> table, Class<? extends Printable> elementClass) {
        try {
            Method getHeadMethod = elementClass.getMethod("getHead");
            io.out.print(getHeadMethod.invoke(elementClass));
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        table.forEach((T e) -> {
            io.out.print(e.getLine());
        });

        io.out.println();

    }

}
