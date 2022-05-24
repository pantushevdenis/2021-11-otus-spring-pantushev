package ru.otus.spring.pantushev.domain;

import org.apache.commons.lang3.time.DateUtils;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Домен Jenre")
public class JenreTest {

    @Test
    @DisplayName("Создается конструктором со всеми параметрами")
    public void shouldConstructFull() {
        ObjectId objectId = new ObjectId();
        Jenre j = new Jenre(objectId, objectId.toString(), "FullName", "ShortName");
        assertEquals(objectId, j.getId());
        assertEquals(objectId.toString(), j.getUserId());
        assertEquals("FullName", j.getFullName());
        assertEquals("ShortName", j.getShortName());
    }

    @Test
    @DisplayName("Создается конструктором с id созданными конструктором")
    public  void shouldConstructShort() {
        Jenre j = new Jenre("FullName", "ShortName");
        assertNotNull(j.getId());
        assertNotNull(j.getUserId());
        assertNotEquals("", j.getUserId());
        assertEquals("FullName", j.getFullName());
        assertEquals("ShortName", j.getShortName());
    }

}
