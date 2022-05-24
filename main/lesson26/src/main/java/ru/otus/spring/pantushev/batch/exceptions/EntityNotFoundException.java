package ru.otus.spring.pantushev.batch.exceptions;

public class EntityNotFoundException extends Exception{
    public EntityNotFoundException(String message) {
        super(message);
    }
}
