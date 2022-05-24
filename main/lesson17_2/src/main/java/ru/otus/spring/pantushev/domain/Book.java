package ru.otus.spring.pantushev.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.spring.pantushev.dto.BookDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BOOKS")
@NamedEntityGraph(
    name = "Book.BookAndDict",
    attributeNodes = {
        @NamedAttributeNode("author"),
        @NamedAttributeNode("jenre")
    }
)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AuthorId", referencedColumnName = "Id")
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JenreId", referencedColumnName = "Id")
    private Jenre jenre;

    @Column(name = "PublishingYear")
    private Integer publishingYear;

    @OneToMany(
        mappedBy = "book",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private List<Comment> comments = new ArrayList<>();

    public Book(long id, String name, Author author, Jenre jenre, Integer publishingYear) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.jenre = jenre;
        this.publishingYear = publishingYear;
    }

    public Book(String name, Author author, Jenre jenre, Integer publishingYear, List<Comment> comments) {
        this.name = name;
        this.author = author;
        this.jenre = jenre;
        this.publishingYear = publishingYear;
        this.comments = comments;
    }


    public Book(String name, Author author, Jenre jenre, Integer publishingYear) {
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
        return (id == book.id) && name.equals(book.name) && author.equals(book.author) && jenre.equals(book.jenre) && Objects.equals(publishingYear, book.publishingYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, jenre, publishingYear);
    }
}
