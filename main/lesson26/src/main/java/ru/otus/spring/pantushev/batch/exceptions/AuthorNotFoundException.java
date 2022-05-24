package ru.otus.spring.pantushev.batch.exceptions;

public class AuthorNotFoundException extends EntityNotFoundException {
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
