package ru.otus.spring.pantushev.dto;

import lombok.*;
import ru.otus.spring.pantushev.domain.Author;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthorDto {
    private long id;
    private String shortName;

    public AuthorDto(Author author) {
        this.id = author.getId();
        this.shortName = author.getShortName();
    }

    public Author toDomain() {
        return new Author(id, null, getShortName(), null);
    }
}
