package ru.otus.pantushev.exceptions;

public class LanguageDataWrongException extends RuntimeException {
    public LanguageDataWrongException(String message) {
        super("Ошибка ввода данных: " + message);
    }
}

