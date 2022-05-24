package ru.otus.spring.pantushev.domain;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Домен Book")
public class BookTest {
    @Test
    @DisplayName("Правильно создаваться конструктором RequiredArgsConstructor со всеми параметрами без identity")
    void shouldCreateOnRequiredArgsConstructor() {
        Book b = new Book("Book1", 1, 1, 2001);
    }

    @Test
    @DisplayName("Правильно создаваться конструктором RequiredArgsConstructor без обязательных параметров без identity")
    void shouldCreateOnRequiredArgsConstructorWithNull () {
        Book b = new Book("Book1", 1, 1, null);
    }

    @Test
    @DisplayName("Правильно создаваться конструктором AllArgsConstructor со всеми параметрами с identity")
    void shouldCreateOnAllArgsConstructor() {
        Book b = new Book(1L, "Book1", 1, 1, 2001);
    }

    @Test
    @DisplayName("Правильно создаваться конструктором AllArgsConstructor без обязательных параметров с identity")
    void shouldCreateOnAllArgsConstructorWithNull() {
        Book b = new Book(1L, "Book1", 1, 1, null);
    }
}
