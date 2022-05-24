package ru.otus.spring.pantushev.domain.views;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.otus.spring.pantushev.domain.Printable;

@Data
@RequiredArgsConstructor
public class BookViewAll
        implements Printable
{
    private final Long id;
    private final String name;
    private final long authorID;
    private final String authorName;
    private final long jenreID;
    private final String jenreName;
    private final Integer publishingYear;

    //@Override
    public static String getHead() {
        return bar +
                "ID\tName\tAuthorID\tAuthorName\tJenreID\tJenreName\tPublishingYear\n" +
                bar;
    }

    @Override
    public String getLine() {
        return String.format("%d\t%s\t%d\t%s\t%d\t%s\t%s\n",
                this.id,
                this.name,
                this.authorID,
                this.authorName,
                this.jenreID,
                this.jenreName,
                this.publishingYear != null ? this.publishingYear.toString() : "null"
        );
    }

    private static final String bar = "----------------------------------------------------------------------------------\n";
}
