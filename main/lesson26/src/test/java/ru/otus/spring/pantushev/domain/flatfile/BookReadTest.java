package ru.otus.spring.pantushev.domain.flatfile;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты BookReadTest")
public class BookReadTest {
    private static final String NAME1 = "name1";
    private static final String AUTHOR_NAME1 = "authorName1";
    private static final String JENRE_NAME1 = "jenreName";
    private static final String PUBLISHING_YEAR1 = "2001";


    @Test
    @DisplayName("Конструктор со всеми параметрами")
    public void shouldCreateWithAllArgsConstructor() {
        BookRead br = new BookRead(NAME1, AUTHOR_NAME1, JENRE_NAME1, PUBLISHING_YEAR1);

        assertEquals(br.getName(), NAME1);
        assertEquals(br.getAuthorName(), AUTHOR_NAME1);
        assertEquals(br.getJenreName(), JENRE_NAME1);
        assertEquals(br.getPublishingYear(), PUBLISHING_YEAR1);
    }
}