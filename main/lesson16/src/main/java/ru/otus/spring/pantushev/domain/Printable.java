package ru.otus.spring.pantushev.domain;

public interface Printable {
    static String getHead() { return ""; };
    String getLine();
}
