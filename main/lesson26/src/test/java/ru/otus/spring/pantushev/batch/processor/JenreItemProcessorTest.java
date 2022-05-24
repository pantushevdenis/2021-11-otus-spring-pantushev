package ru.otus.spring.pantushev.batch.processor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.pantushev.batch.caches.JenreNameCach;
import ru.otus.spring.pantushev.domain.flatfile.BookRead;
import ru.otus.spring.pantushev.domain.mongodb.Author;
import ru.otus.spring.pantushev.domain.mongodb.Jenre;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JenreItemProcessorTest {
    private static final String NAME1 = "name1";
    private static final String AUTHOR_NAME1 = "authorName1";
    private static final String JENRE_NAME1 = "jenreName";
    private static final String PUBLISHING_YEAR1 = "2001";

    @Mock
    JenreNameCach jenreNameCach;

    @InjectMocks
    JenreItemProcessor jenreItemProcessor;

    @Test
    @DisplayName("Должен правильно преобразовать Жанр из строки плоского файла, если строки нет в кэше")
    void shouldProcessToAuthor() {
        BookRead bookRead = new BookRead(NAME1, AUTHOR_NAME1, JENRE_NAME1, PUBLISHING_YEAR1);
        Mockito.when(jenreNameCach.exists(Mockito.anyString())).thenReturn(false);

        Jenre result = jenreItemProcessor.process(bookRead);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(result.getFullName(), JENRE_NAME1);
        assertEquals(result.getShortName(), JENRE_NAME1);
    }

    @Test
    @DisplayName("Должен вернуть null из строки плоского файла, если строка есть в кэше")
    void shouldPocessToNull() {
        BookRead bookRead = new BookRead(NAME1, AUTHOR_NAME1, JENRE_NAME1, PUBLISHING_YEAR1);
        Mockito.when(jenreNameCach.exists(Mockito.anyString())).thenReturn(true);

        Jenre result = jenreItemProcessor.process(bookRead);
        assertNull(result);
    }

}