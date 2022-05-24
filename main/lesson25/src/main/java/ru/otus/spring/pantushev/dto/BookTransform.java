package ru.otus.spring.pantushev.dto;

import ru.otus.spring.pantushev.entities.Book;

public class BookTransform {
    public static BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getName(), book.getAuthor(), book.getJenre(), book.getPublishingYear());
    }

    public static Book toEntity(BookDto dto) {
        return new Book(dto.getId(), dto.getName(), dto.getAuthor(), dto.getJenre(), dto.getPublishingYear());
    }
}
