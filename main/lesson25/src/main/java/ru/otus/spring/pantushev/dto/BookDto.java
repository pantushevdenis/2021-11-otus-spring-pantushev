package ru.otus.spring.pantushev.dto;

import lombok.*;
import ru.otus.spring.pantushev.entities.Author;
import ru.otus.spring.pantushev.entities.Jenre;

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
}
