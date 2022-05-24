package ru.otus.spring.pantushev.domain.mongodb.bookDocument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import ru.otus.spring.pantushev.domain.mongodb.Jenre;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JenreElement
{
    @Id
    private String id;
    private String fullName;
}
