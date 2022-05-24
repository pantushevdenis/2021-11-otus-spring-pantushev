package ru.otus.pantushev.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableNotFoundException extends RuntimeException {
    private String id;

    public TableNotFoundException(String id) {
        super("Table not found: " + id);
        this.id = id;
    }
}
