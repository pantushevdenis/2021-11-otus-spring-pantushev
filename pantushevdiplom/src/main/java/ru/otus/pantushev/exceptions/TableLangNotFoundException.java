package ru.otus.pantushev.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableLangNotFoundException extends RuntimeException {
    private final String id;
    private final String languageCd;

    public TableLangNotFoundException(String id, String languageCd) {
        super("Table not found: " + id + ", " + languageCd);
        this.id = id;
        this.languageCd = languageCd;
    }
}
