package ru.otus.pantushev.exceptions;

public class FieldDataWrongException extends RuntimeException {
    public FieldDataWrongException(String message) {
        super("Ошибка ввода данных: " + message);
    }
}

