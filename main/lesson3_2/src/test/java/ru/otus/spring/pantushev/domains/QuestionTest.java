package ru.otus.spring.pantushev.domains;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Question domain test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QuestionTest {

    QuestionHead qh;
    Question q1;

    @BeforeAll
    void init()
    {
        qh = new QuestionHead(1, "body");
        q1 = new Question(qh, 1);
    }

    @Test
    @DisplayName("Constructor test")
    void testConstructor()
    {
        assertNotNull(q1.getHead());
        assertEquals(qh, q1.getHead());
        assertEquals(1, q1.getIdCorrectAnswer());
        assertNotNull(q1.getAnswers());
    }

    @Test
    @DisplayName("getId test")
    void getIdTest()
    {
        assertEquals(1, q1.getId());
    }

    @Test
    @DisplayName("getBody test")
    void getBodyTest()
    {
        assertEquals("body", q1.getBody());
    }
}
