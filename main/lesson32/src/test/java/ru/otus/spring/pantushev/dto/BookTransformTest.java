package ru.otus.spring.pantushev.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.pantushev.entities.Author;
import ru.otus.spring.pantushev.entities.Book;
import ru.otus.spring.pantushev.entities.Jenre;

import static org.junit.jupiter.api.Assertions.*;

class BookTransformTest {
    private static final String BOOK_NAME = "BookName1";
    private static final String AUTHOR_NAME = "AuthorName1";
    private static final String JENRE_NAME = "JenreName1";
    private static final Author AUTHOR = new Author(1L, AUTHOR_NAME);
    private static final Jenre JENRE = new Jenre(1L, JENRE_NAME);
    private static final int YEAR = 2001;
    private static final Book BOOK = new Book(1L, BOOK_NAME, AUTHOR, JENRE, YEAR);
    private static final BookDto BOOK_DTO = new BookDto(1, BOOK_NAME, AUTHOR, JENRE, YEAR);
    @Test
    @DisplayName("Преобразовать объект сущности в dto")
    void toDto() {
        BookDto bookDtoActual = BookTransform.toDto(BOOK);
        assertEquals(BOOK_DTO, bookDtoActual);
    }

    @Test
    @DisplayName("Преобразовать объект dto в сущности")
    void toEntity() {
        Book bookActual = BookTransform.toEntity(BOOK_DTO);
        assertEquals(BOOK, bookActual);
    }
}