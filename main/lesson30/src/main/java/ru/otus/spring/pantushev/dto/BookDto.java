package ru.otus.spring.pantushev.dto;

import lombok.*;
import ru.otus.spring.pantushev.domain.Author;
import ru.otus.spring.pantushev.domain.Book;
import ru.otus.spring.pantushev.domain.Jenre;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private long id;

    @NotBlank(message = "Наименование не должно быть пустым")
    private String name;

    @NotNull(message = "Автор не должен быть пустым")
    private Author author = new Author();

    @NotNull(message = "Жанр не должен быть пустым")
    private Jenre jenre = new Jenre();

    private Integer publishingYear;

    public BookDto(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.author = book.getAuthor();
        this.jenre = book.getJenre();
        this.publishingYear = book.getPublishingYear();
    }
}
