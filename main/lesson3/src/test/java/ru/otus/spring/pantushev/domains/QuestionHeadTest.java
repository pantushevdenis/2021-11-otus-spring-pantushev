package ru.otus.spring.pantushev.domains;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("QuestionHead domai ntest ")
public class QuestionHeadTest {
    @Test
    @DisplayName("Constructor and getters test")
    void constructorAndGettersTest()
    {
        QuestionHead qh = new QuestionHead(1, "body");

        assertEquals(1, qh.getId());
        assertEquals("body", qh.getBody());
    }
}
