package ru.otus.spring.pantushev.domain;

import lombok.*;

import javax.persistence.*;

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
    @JoinColumn(name = "bookId")
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
}
