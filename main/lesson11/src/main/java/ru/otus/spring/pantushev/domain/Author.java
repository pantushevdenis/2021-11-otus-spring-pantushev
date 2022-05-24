package ru.otus.spring.pantushev.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AUTHORS")
public class Author
    implements Printable
{
    @Id
    private long id;

    @Column(unique = true, nullable = false)
    private String name;

    public static String getHead() {
        return bar +
                "ID\tName\n" +
                bar;
    }

    @Override
    public String getLine() {
        return String.format("%d\t%s\n", this.id, this.name);
    }

    private static final String bar = "----------------------------------------------------\n";
}
