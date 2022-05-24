package ru.otus.spring.pantushev.domain;

import org.apache.commons.lang3.time.DateUtils;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Домен Jenre")
public class JenreTest {
    private static final String ID_1 = ObjectId.get().toString();
    private static final String FULL_NAME_1 = "FullName1";
    private static String SHORT_NAME_1 = "ShortName1";

    @Test
    @DisplayName("Создается конструктором со всеми параметрами")
    public void shouldConstructFull() {
        Jenre j = new Jenre(ID_1, FULL_NAME_1, SHORT_NAME_1);

        assertEquals(ID_1, j.getId());
        assertEquals(FULL_NAME_1, j.getFullName());
        assertEquals(SHORT_NAME_1, j.getShortName());
    }

    @Test
    @DisplayName("Создается конструктором с id созданными конструктором")
    public  void shouldConstructShort() {
        Jenre j = new Jenre(FULL_NAME_1, SHORT_NAME_1);

        assertNotNull(j.getId());
        assertTrue(ObjectId.isValid(j.getId()));
        assertEquals(FULL_NAME_1, j.getFullName());
        assertEquals(SHORT_NAME_1, j.getShortName());
    }

}
