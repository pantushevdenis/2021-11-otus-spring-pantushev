package ru.otus.spring.pantushev.domain.mongodb.bookDocument;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.pantushev.domain.mongodb.Author;
import ru.otus.spring.pantushev.domain.mongodb.Jenre;
import ru.otus.spring.pantushev.domain.mongodb.Transform;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тест сервиса преобразования")
public class TransformTest {
    private static final String ID_1 = ObjectId.get().toString();
    private static final String FULL_NAME_1 = "FullName1";
    private static final String SHORT_NAME_1 = "ShortName1";
    private static final Jenre JENRE_1 = new Jenre(ID_1, FULL_NAME_1, SHORT_NAME_1);

    private static final Date DATE_1 = null;
    private static final Author AUTHOR_1 = new Author(ID_1, FULL_NAME_1, SHORT_NAME_1, DATE_1);


    @Test
    @DisplayName("Создается элемент документа JenreElement из домена Jenre")
    public void shouldConstructFromJenre() {
        JenreElement je = Transform.jenreToJenreElement(JENRE_1);

        assertEquals(ID_1, je.getId());
        assertEquals(FULL_NAME_1, je.getFullName());
    }

    @Test
    @DisplayName("Создается конструктором из домена Author")
    public void shouldConstructFromAuthor() {
        AuthorElement ae = Transform.authorToAuthorElement(AUTHOR_1);

        assertEquals(ID_1, ae.getId());
        assertEquals(FULL_NAME_1, ae.getFullName());
    }
}
