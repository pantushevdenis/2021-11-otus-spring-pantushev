package ru.otus.spring.pantushev.domain.bookDocument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JenreElement
{
    @Id
    private String id;
    private String fullName;

    public JenreElement(ru.otus.spring.pantushev.domain.Jenre jenre) {
        this.id = jenre.getId();
        this.fullName = jenre.getFullName();
    }
}
