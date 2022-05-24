package ru.otus.spring.pantushev.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BOOKS")
@Builder(

)
public class Book implements Printable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public static BookBuilder builder(String name, Author author, Jenre jenre) {
        return new BookBuilder()
            .name(name)
            .author(author)
            .jenre(jenre);
    }

    @OneToMany(
        mappedBy = "book",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private List<Comment> comments;


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
        return String.format("%d\t%s\t%d\t%s\t%d\t%s\t%s\n",
                this.id,
                this.name,
                this.author.getId(),
                this.author.getName(),
                this.jenre.getId(),
                this.jenre.getName(),
                this.publishingYear != null ? this.publishingYear.toString() : "null"
        );
    }

    private static final String bar = "----------------------------------------------------------------------------------\n";


}
