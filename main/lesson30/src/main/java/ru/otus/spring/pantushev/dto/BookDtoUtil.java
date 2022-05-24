package ru.otus.spring.pantushev.dto;

import ru.otus.spring.pantushev.domain.Book;

public class BookDtoUtil {
    public static Book toDomain(BookDto dto) {
        return new Book(dto.getId(), dto.getName(), dto.getAuthor(), dto.getJenre(), dto.getPublishingYear());
    }

    public static BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getName(), book.getAuthor(), book.getJenre(), book.getPublishingYear());
    }
}
