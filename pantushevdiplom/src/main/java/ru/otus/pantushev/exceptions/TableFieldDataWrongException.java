package ru.otus.pantushev.exceptions;

public class TableFieldDataWrongException extends RuntimeException {
    public TableFieldDataWrongException(String message) {
        super("Ошибка ввода данных: " + message);
    }
}

