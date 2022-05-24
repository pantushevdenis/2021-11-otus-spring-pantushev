package ru.otus.spring.pantushev.exceptions;


public class BookDataWrongException extends RuntimeException {
    public BookDataWrongException(String message) {
        super("Ошибка ввода данных: " + message);
    }
}
