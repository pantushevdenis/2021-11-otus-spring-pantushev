package ru.otus.spring.pantushev.entities.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityKey implements Serializable {
    private String userName;
    private String authority;
}
