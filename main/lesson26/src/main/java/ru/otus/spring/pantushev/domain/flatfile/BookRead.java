package ru.otus.spring.pantushev.domain.flatfile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRead {
    private String name;
    private String authorName;
    private String jenreName;
    private String publishingYear;
}
