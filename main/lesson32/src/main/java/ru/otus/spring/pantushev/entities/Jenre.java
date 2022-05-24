package ru.otus.spring.pantushev.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "JENRES")
public class Jenre {
    @Id
    private long id;

    @Column(unique = true, nullable = false)
    private String name;
}
