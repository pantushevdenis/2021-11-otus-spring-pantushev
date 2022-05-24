package ru.otus.spring.pantushev.domain;

import org.apache.commons.lang3.time.DateUtils;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Домен Author")
public class AuthorTest {
    private static final String ID_1 = ObjectId.get().toString();
    private static final String FULL_NAME_1 = "FullName1";
    private static String SHORT_NAME_1 = "ShortName1";
    private static Date DATE_1;

    static {
        try {
            DATE_1 = DateUtils.parseDate("01-02-2001", "dd-mm-yyyy");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Создается конструктором со всеми параметрами")
    public void shouldConstructFull() throws ParseException {
        Author a = new Author(ID_1, FULL_NAME_1, SHORT_NAME_1, DATE_1);

        assertEquals(ID_1, a.getId());
        assertEquals(FULL_NAME_1, a.getFullName());
        assertEquals(SHORT_NAME_1, a.getShortName());
        assertEquals(DATE_1, a.getDateOfBirdth());
    }


    @Test
    @DisplayName("Создается конструктором с id созданными конструктором")
    public  void shouldConstructShort() throws ParseException {
        Author a = new Author(FULL_NAME_1, SHORT_NAME_1, DATE_1);

        assertNotNull(a.getId());
        assertTrue(ObjectId.isValid(a.getId()));
        assertEquals(FULL_NAME_1, a.getFullName());
        assertEquals(SHORT_NAME_1, a.getShortName());
        assertEquals(DATE_1, a.getDateOfBirdth());
    }
}
