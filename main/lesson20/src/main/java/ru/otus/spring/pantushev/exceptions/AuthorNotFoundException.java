package ru.otus.spring.pantushev.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorNotFoundException extends RuntimeException {
    private String id;

    public AuthorNotFoundException(String id) {
        super("Author not found:" + id);
        this.id = id;
    }
}
