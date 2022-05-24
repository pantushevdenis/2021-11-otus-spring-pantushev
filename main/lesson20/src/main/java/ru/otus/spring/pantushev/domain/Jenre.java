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
public class Jenre {
    @Id
    private String id;
    private String fullName;
    private String shortName;

    public Jenre(String fullName, String shortName) {
        this.id = ObjectId.get().toString();
        this.fullName = fullName;
        this.shortName = shortName;
    }
}
