package ru.otus.pantushev.exceptions;

public class TableDataWrongException extends RuntimeException {
    public TableDataWrongException(String message) {
        super("Ошибка ввода данных: " + message);
    }
}

