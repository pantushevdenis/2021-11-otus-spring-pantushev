package ru.otus.spring.pantushev.batch.processor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.pantushev.batch.caches.AuthorNameCach;
import ru.otus.spring.pantushev.domain.flatfile.BookRead;
import ru.otus.spring.pantushev.domain.mongodb.Author;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthorItemProcessorTest {
    private static final String NAME1 = "name1";
    private static final String AUTHOR_NAME1 = "authorName1";
    private static final String JENRE_NAME1 = "jenreName";
    private static final String PUBLISHING_YEAR1 = "2001";

    @Mock
    AuthorNameCach authorNameCach;

    @InjectMocks
    AuthorItemProcessor authorItemProcessor;

    @Test
    @DisplayName("Должен правильно преобразовать Автора из строки плоского файла, если строки нет в кэше")
    void shouldProcessToAuthor() {
        BookRead bookRead = new BookRead(NAME1, AUTHOR_NAME1, JENRE_NAME1, PUBLISHING_YEAR1);
        Mockito.when(authorNameCach.exists(Mockito.anyString())).thenReturn(false);

        Author result = authorItemProcessor.process(bookRead);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(result.getFullName(), AUTHOR_NAME1);
        assertEquals(result.getShortName(), AUTHOR_NAME1);
        assertNull(result.getDateOfBirdth());
    }

    @Test
    @DisplayName("Должен вернуть null из строки плоского файла, если строка есть в кэше")
    void shouldPocessToNull() {
        BookRead bookRead = new BookRead(NAME1, AUTHOR_NAME1, JENRE_NAME1, PUBLISHING_YEAR1);
        Mockito.when(authorNameCach.exists(Mockito.anyString())).thenReturn(true);

        Author result = authorItemProcessor.process(bookRead);
        assertNull(result);
    }

}