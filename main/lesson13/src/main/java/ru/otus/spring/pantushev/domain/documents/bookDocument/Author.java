package ru.otus.spring.pantushev.domain.documents.bookDocument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.otus.spring.pantushev.domain.Printable;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author
{
    @Id
    private ObjectId id;
    private String fullName;

    public Author(ru.otus.spring.pantushev.domain.Author author) {
        this.id = author.getId();
        this.fullName = author.getFullName();
    }
}
