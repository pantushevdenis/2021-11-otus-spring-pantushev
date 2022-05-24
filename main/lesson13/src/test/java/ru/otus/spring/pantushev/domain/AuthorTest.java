package ru.otus.spring.pantushev.domain;

import org.apache.commons.lang3.time.DateUtils;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Домен Author")
public class AuthorTest {

    @Test
    @DisplayName("Создается конструктором со всеми параметрами")
    public void shouldConstructFull() throws ParseException {
        ObjectId objectId = new ObjectId();
        Date date = DateUtils.parseDate("01-02-2001", "dd-mm-yyyy");
        Author a = new Author(objectId, objectId.toString(), "FullName", "ShortName", date);
        assertEquals(objectId, a.getId());
        assertEquals(objectId.toString(), a.getUserId());
        assertEquals("FullName", a.getFullName());
        assertEquals("ShortName", a.getShortName());
        assertEquals(date, a.getDateOfBirdth());
    }


    @Test
    @DisplayName("Создается конструктором с id созданными конструктором")
    public  void shouldConstructShort() throws ParseException {
        Date date = DateUtils.parseDate("01-02-2001", "dd-mm-yyyy");
        Author a = new Author("FullName", "ShortName", date);
        assertNotNull(a.getId());
        assertNotNull(a.getUserId());
        assertNotEquals("", a.getUserId());
        assertEquals("FullName", a.getFullName());
        assertEquals("ShortName", a.getShortName());
        assertEquals(date, a.getDateOfBirdth());
    }

}
