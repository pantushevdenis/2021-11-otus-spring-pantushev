package ru.otus.spring.pantushev.domain.documents.bookDocument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import ru.otus.spring.pantushev.domain.Printable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jenre
{
    @Id
    private ObjectId id;
    private String fullName;

    public Jenre(ru.otus.spring.pantushev.domain.Jenre jenre) {
        this.id = jenre.getId();
        this.fullName = jenre.getFullName();
    }
}
