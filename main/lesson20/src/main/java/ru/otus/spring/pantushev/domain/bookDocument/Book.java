package ru.otus.spring.pantushev.domain.bookDocument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document()
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    private String id;
    private String name;
    private AuthorElement author;
    private JenreElement jenre;
    private Integer publishingYear;

    public Book(String name, AuthorElement author, JenreElement jenre, Integer publishingYear) {
        this.id = ObjectId.get().toString();
        this.name = name;
        this.author = author;
        this.jenre = jenre;
        this.publishingYear = publishingYear;
    }

    public Book(Book value) {
        this(value.id, value.name, value.author, value.jenre, value.publishingYear);
    }
}
