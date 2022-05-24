package ru.otus.spring.pantushev.domain.documents.bookDocument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.otus.spring.pantushev.domain.Printable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document()
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Printable {
    @Id
    private ObjectId id;

    private String name;
    private Author author;
    private Jenre jenre;
    private Integer publishingYear;
    private List<Comment> comments = new ArrayList<>();

    public Book(ObjectId id, String name, Author author, Jenre jenre, Integer publishingYear) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.jenre = jenre;
        this.publishingYear = publishingYear;
    }

    public Book(String name, Author author, Jenre jenre, Integer publishingYear, List<Comment> comments) {
        this.id = new ObjectId();
        this.name = name;
        this.author = author;
        this.jenre = jenre;
        this.publishingYear = publishingYear;
        this.comments = comments;
    }

    public Book(String name, Author author, Jenre jenre, Integer publishingYear) {
        this.id = new ObjectId();
        this.name = name;
        this.author = author;
        this.jenre = jenre;
        this.publishingYear = publishingYear;
        this.comments = null;
    }

    public Book(Book value) {
        this(value.id, value.name, value.author, value.jenre, value.publishingYear, value.comments);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id.equals(book.id) && name.equals(book.name) && author.equals(book.author) && jenre.equals(book.jenre) && Objects.equals(publishingYear, book.publishingYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, jenre, publishingYear);
    }

    public static String getHead() {
        return bar +
                "ID\tName\tAuthorID\tAutorName\tJenreID\tJenreName\ttPublishingYear\n" +
                bar;
    }

    @Override
    public String getLine() {
        Author author = getAuthor();
        Jenre jenre = getJenre();
        return String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\n",
                this.id.toString(),
                this.name,
                this.author.getId().toString(),
                this.author.getFullName(),
                this.jenre.getId().toString(),
                this.jenre.getFullName(),
                this.publishingYear != null ? this.publishingYear.toString() : "null"
        );
    }

    private static final String bar = "----------------------------------------------------------------------------------\n";
}
