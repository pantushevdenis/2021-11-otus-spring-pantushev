package ru.otus.spring.pantushev.domains;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Answer domain test ")
public class AnswerTest {

    @DisplayName("Корректный конструктор")
    @Test
    void testConstructor()
    {
        Answer a1 = new Answer("body");

        assertEquals("body", a1.getBody());
    }

}
