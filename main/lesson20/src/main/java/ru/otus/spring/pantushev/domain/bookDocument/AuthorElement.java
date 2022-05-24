package ru.otus.spring.pantushev.domain.bookDocument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorElement
{
    @Id
    private String id;
    private String fullName;

    public AuthorElement(ru.otus.spring.pantushev.domain.Author author) {
        this.id = author.getId();
        this.fullName = author.getFullName();
    }
}
