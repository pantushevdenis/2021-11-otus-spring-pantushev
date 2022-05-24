package ru.otus.spring.pantushev.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookSaveDto {
    private String id;
    private String name;
    private String authorId;
    private String jenreId;
    private Integer publishingYear;
}
