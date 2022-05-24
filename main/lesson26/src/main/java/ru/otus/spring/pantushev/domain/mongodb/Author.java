package ru.otus.spring.pantushev.domain.mongodb;

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
public class Author {
    @Id
    private String id;
    private String fullName;
    private String shortName;
    private Date dateOfBirdth;

    public Author(String fullName, String shortName, Date dateOfBirdth) {
        this.id = ObjectId.get().toString();
        this.fullName = fullName;
        this.shortName = shortName;
        this.dateOfBirdth = dateOfBirdth;
    }
}
