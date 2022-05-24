package ru.otus.spring.pantushev.batch.exceptions;

public class JenreNotFoundException extends EntityNotFoundException{
    public JenreNotFoundException(String message) {
        super(message);
    }
}
