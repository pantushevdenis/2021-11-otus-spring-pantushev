package ru.otus.spring.pantushev.domain.mongodb.bookDocument;

import org.apache.commons.lang3.time.DateUtils;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Домен AuthorElement")
class AuthorElementTest {
    private static final String ID_1 = ObjectId.get().toString();
    private static final String FULL_NAME_1 = "FullName1";
    private static final String SHORT_NAME_1 = "ShortName1";
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
    public void shouldConstructFull() {
        AuthorElement ae = new AuthorElement(ID_1, FULL_NAME_1);

        assertEquals(ID_1, ae.getId());
        assertEquals(FULL_NAME_1, ae.getFullName());
    }
}