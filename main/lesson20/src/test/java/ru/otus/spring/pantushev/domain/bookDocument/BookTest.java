package ru.otus.spring.pantushev.domain.bookDocument;


import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Домен Book")
public class BookTest {
    private static final String ID_1 = ObjectId.get().toString();
    private static final String BOOK_NAME_1 = "Book1";
    private static final AuthorElement AUTHOR_ELEMENT_1 = new AuthorElement(ObjectId.get().toString(), "Author1");
    private static final JenreElement JENRE_ELEMENT_1 = new JenreElement(ObjectId.get().toString(), "Jenre1");
    private static final Integer YEAR_1 = 2001;

    @Test
    @DisplayName("Правильно создаваться конструктором со всеми параметрами без id")
    void shouldCreateOnRequiredArgsConstructor() {
        Book b = new Book(BOOK_NAME_1, AUTHOR_ELEMENT_1, JENRE_ELEMENT_1, YEAR_1);

        assertNotNull(b.getId());
        assertTrue(ObjectId.isValid(b.getId()));
        assertEquals(BOOK_NAME_1, b.getName());
        assertEquals(AUTHOR_ELEMENT_1, b.getAuthor());
        assertEquals(JENRE_ELEMENT_1, b.getJenre());
        assertEquals(YEAR_1, b.getPublishingYear());
    }

    @Test
    @DisplayName("Правильно создаваться конструктором со всеми параметрами с id")
    void shouldCreateOnAllArgsConstructor() {
        Book b = new Book(ID_1, BOOK_NAME_1, AUTHOR_ELEMENT_1, JENRE_ELEMENT_1, YEAR_1);

        assertEquals(ID_1, b.getId());
        assertEquals(BOOK_NAME_1, b.getName());
        assertEquals(AUTHOR_ELEMENT_1, b.getAuthor());
        assertEquals(JENRE_ELEMENT_1, b.getJenre());
        assertEquals(YEAR_1, b.getPublishingYear());
    }
}
