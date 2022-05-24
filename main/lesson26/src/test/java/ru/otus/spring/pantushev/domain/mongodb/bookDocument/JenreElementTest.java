package ru.otus.spring.pantushev.domain.mongodb.bookDocument;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Домен JenreElement")
public class JenreElementTest {
    private static final String ID_1 = ObjectId.get().toString();
    private static final String FULL_NAME_1 = "FullName1";
    private static final String SHORT_NAME_1 = "ShortName1";


    @Test
    @DisplayName("Создается конструктором со всеми параметрами")
    public void shouldConstructFull() {
        JenreElement je = new JenreElement(ID_1, FULL_NAME_1);

        assertEquals(ID_1, je.getId());
        assertEquals(FULL_NAME_1, je.getFullName());
    }
}
