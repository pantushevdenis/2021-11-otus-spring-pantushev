package ru.otus.spring.pantushev.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
}
