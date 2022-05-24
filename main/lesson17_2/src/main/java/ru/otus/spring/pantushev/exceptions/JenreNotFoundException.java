package ru.otus.spring.pantushev.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JenreNotFoundException extends RuntimeException {
    private long id;

    public JenreNotFoundException(long id) {
        super("Jenge not found: " + id);
        this.id = id;
    }
}
