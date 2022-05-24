package ru.otus.spring.pantushev.domain.mongodb.bookDocument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import ru.otus.spring.pantushev.domain.mongodb.Author;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorElement
{
    @Id
    private String id;
    private String fullName;
}
