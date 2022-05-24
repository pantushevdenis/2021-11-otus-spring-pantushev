package ru.otus.spring.pantushev.domain.bookDocument;

import org.apache.commons.lang3.time.DateUtils;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.pantushev.domain.Author;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.Date;

@DisplayName("Домен AuthorElement")
class AuthorElementTest {
    private static final String ID_1 = ObjectId.get().toString();
    private static final String FULL_NAME_1 = "FullName1";
    private static String SHORT_NAME_1 = "ShortName1";
    private static Date DATE_1;
    private static Author AUTHOR_1 = new Author(ID_1, FULL_NAME_1, SHORT_NAME_1, DATE_1);

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

    @Test
    @DisplayName("Создается конструктором из домена Author")
    public void shouldConstructFromAuthor() {
        AuthorElement ae = new AuthorElement(AUTHOR_1);

        assertEquals(ID_1, ae.getId());
        assertEquals(FULL_NAME_1, ae.getFullName());
    }


}