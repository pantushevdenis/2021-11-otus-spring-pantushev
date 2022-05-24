package ru.otus.spring.pantushev.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author
    implements Printable
{
    @Id
    private ObjectId id;
    private String userId;
    private String fullName;
    private String shortName;
    private Date dateOfBirdth;

    public static String getHead() {
        return bar +
                "ID\tName\tDateOfBirdth\n" +
                bar;
    }

    public Author(String fullName, String shortName, Date dateOfBirdth) {
        this.id = new ObjectId();
        this.userId = this.id.toString();
        this.fullName = fullName;
        this.shortName = shortName;
        this.dateOfBirdth = dateOfBirdth;
    }

    @Override
    public String getLine() {
        return String.format("%s\t%s\t%s\t%s\n", this.id.toString(), this.fullName, this.shortName, this.dateOfBirdth.toString());
    }

    private static final String bar = "----------------------------------------------------\n";
}
