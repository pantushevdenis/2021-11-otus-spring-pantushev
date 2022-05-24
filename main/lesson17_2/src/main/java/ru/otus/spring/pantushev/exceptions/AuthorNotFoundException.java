package ru.otus.spring.pantushev.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorNotFoundException extends RuntimeException {
    private long id;

    public AuthorNotFoundException(long id) {
        super("Author not found:" + id);
        this.id = id;
    }
}
