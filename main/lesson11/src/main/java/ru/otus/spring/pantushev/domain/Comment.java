package ru.otus.spring.pantushev.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COMMENTS")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Content", nullable = false)
    private String content;

    @ManyToOne()
    @JoinColumn(
        name = "BookId",
        referencedColumnName = "ID"
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Book book;

    @ToString.Include
    @EqualsAndHashCode.Include
    public long getBookId() {
        if (book != null) {
            return book.getId();
        }
        else {
            return 0;
        }

    }

    public Comment(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id.equals(comment.id) && content.equals(comment.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content);
    }

     */
}
