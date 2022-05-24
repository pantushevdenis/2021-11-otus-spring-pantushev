package ru.otus.spring.pantushev.domain.documents.bookDocument;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.otus.spring.pantushev.domain.Printable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment
        implements Printable
{

    @Id
    private ObjectId id;
    private String content;

    public static String getHead() {
        return bar +
                "ID\tContent\n" +
                bar;
    }

    @Override
    public String getLine() {
        return String.format("%s\t%s\n", this.id.toString(), this.content);
    }

    private static final String bar = "----------------------------------------------------\n";

}
