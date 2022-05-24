package ru.otus.spring.pantushev.domains;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("StudentResponse domain test")
public class StudentResponseTest {
    @Test@DisplayName("Constructor and getters test")
    void constructorAndGettersTest()
    {
        StudentResponse sr = new StudentResponse(1, 2);

        assertEquals(1, sr.getIdQuestion());
        assertEquals(2, sr.getIdAnswer());
    }
}
