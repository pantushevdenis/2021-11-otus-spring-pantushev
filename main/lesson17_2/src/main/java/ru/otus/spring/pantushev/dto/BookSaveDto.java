package ru.otus.spring.pantushev.dto;


import lombok.*;
import ru.otus.spring.pantushev.domain.Author;
import ru.otus.spring.pantushev.domain.Book;
import ru.otus.spring.pantushev.domain.Jenre;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookSaveDto {
    private long id;
    private String name;
    private long authorId;
    private long jenreId;
    private Integer publishingYear;
}
