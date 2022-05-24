package ru.otus.spring.pantushev.domains;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("QuestionQuantity domain test")
public class QuestionQuantityTest {
    @Test
    @DisplayName("Constructor and getters test")
     void constructorAndGettersTest()
    {
        QuestionQuantity qq = new QuestionQuantity(1);

        assertEquals(1, qq.getQuantity());
    }
}
