package ru.otus.pantushev.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldNotFoundException extends RuntimeException {
    private String id;

    public FieldNotFoundException(String id) {
        super("Ffield not found: " + id);
        this.id = id;
    }
}
