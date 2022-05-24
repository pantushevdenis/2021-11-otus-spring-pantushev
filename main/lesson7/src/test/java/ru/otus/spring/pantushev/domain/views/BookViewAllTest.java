package ru.otus.spring.pantushev.domain.views;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тест вьюхи BookViewAll")
class BookViewAllTest {
    @Test
    @DisplayName("Правильно инициализируется конструктором со всеми полями.")
    void shouldCorrectConstruct() {
        BookViewAll b = new BookViewAll(1L, "Book1", 2L, "Author1", 3L, "Jenre1", 2001);
    }

}