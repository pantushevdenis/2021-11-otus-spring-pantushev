package ru.otus.spring.pantushev.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Data
@AllArgsConstructor
public class Book
    implements Printable
{
    private Long id;
    private String name;
    private long authorID;
    private long jenreID;
    private Integer publishingYear;

    public Book(String name, long authorID, long jenreID, Integer publishingYear) {
        this.name = name;
        this.authorID = authorID;
        this.jenreID = jenreID;
        this.publishingYear = publishingYear;
    }

    //@Override
    public static String getHead() {
        return bar +
                "ID\tName\tAuthorID\tJenreID\ttPublishingYear\n" +
                bar;
    }

    @Override
    public String getLine() {
        return String.format("%d\t%s\t%d\t%d\t%s\n",
                this.id,
                this.name,
                this.authorID,
                this.jenreID,
                this.publishingYear != null ? this.publishingYear.toString() : "null"
        );
    }

    private static final String bar = "----------------------------------------------------------------------------------\n";


}
