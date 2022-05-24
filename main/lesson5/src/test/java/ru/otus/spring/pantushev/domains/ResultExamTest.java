package ru.otus.spring.pantushev.domains;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ResultExam domain test")
public class ResultExamTest {
    @Test
    @DisplayName("Constructor and getters test")
    void constructorAndGettersTest()
    {
        ResultExam re = new ResultExam(10, 1, 50.5, true);

        assertEquals(10, re.getAnswersTotal());
        assertEquals( 1, re.getAnswersCorrect());
        assertEquals(50.5, re.getPercentageOfCorrectAnswers());
        assertEquals(true, re.getExamSuccesful());
    }
}
