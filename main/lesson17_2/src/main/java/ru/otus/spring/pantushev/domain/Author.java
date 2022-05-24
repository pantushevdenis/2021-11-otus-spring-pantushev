package ru.otus.spring.pantushev.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AUTHORS")
public class Author {
    @Id
    private Long id;

    @Column(unique = true, nullable = false)
    private String fullName;
    @Column(unique = true, nullable = false)
    private String shortName;
    @Column(nullable = false)
    private LocalDate dateOfBirdth;
}
