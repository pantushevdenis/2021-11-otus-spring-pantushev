package ru.otus.spring.pantushev.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jenre
        implements Printable
{
    @Id
    private ObjectId id;
    private String userId;
    private String fullName;
    private String shortName;

    public static String getHead() {
        return bar +
                "ID\tName\n" +
                bar;
    }

    public Jenre(String fullName, String shortName) {
        this.id = new ObjectId();
        this.userId = this.id.toString();
        this.fullName = fullName;
        this.shortName = shortName;
    }

    @Override
    public String getLine() {
        return String.format("%s\t%s\t%s\n", this.id.toString(), this.fullName, this.shortName);
    }

    private static final String bar = "----------------------------------------------------\n";
}
