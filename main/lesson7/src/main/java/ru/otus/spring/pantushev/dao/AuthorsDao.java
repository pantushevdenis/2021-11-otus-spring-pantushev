package ru.otus.spring.pantushev.dao;

import ru.otus.spring.pantushev.domain.Author;

import java.util.List;

public interface AuthorsDao {
    List<Author> getAll();
}
