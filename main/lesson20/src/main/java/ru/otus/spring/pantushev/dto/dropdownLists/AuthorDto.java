package ru.otus.spring.pantushev.dto.dropdownLists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    @Id
    private String id;
    private String shortName;
}
