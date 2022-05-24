package ru.otus.spring.pantushev.exceptions;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookNotFoundException extends RuntimeException {
    private String id;

    public BookNotFoundException(String id) {
        super("Book not found: " + id);
        this.id = id;
    }
}
