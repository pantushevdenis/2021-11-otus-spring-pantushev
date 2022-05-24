package ru.otus.spring.pantushev.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookNotFoundException extends RuntimeException {
    private long id;

    public BookNotFoundException(long id) {
        super("Book not found: " + id);
        this.id = id;
    }
}
