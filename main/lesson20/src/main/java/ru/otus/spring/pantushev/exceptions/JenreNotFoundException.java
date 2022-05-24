package ru.otus.spring.pantushev.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JenreNotFoundException extends RuntimeException {
    private String id;

    public JenreNotFoundException(String id) {
        super("Jenge not found: " + id);
        this.id = id;
    }
}
