package ru.otus.spring.pantushev.domain.mongodb;

import ru.otus.spring.pantushev.domain.mongodb.bookDocument.AuthorElement;
import ru.otus.spring.pantushev.domain.mongodb.bookDocument.JenreElement;

public class Transform {
    public static AuthorElement authorToAuthorElement(Author author) {
        return new AuthorElement(author.getId(), author.getFullName());
    }

    public static JenreElement jenreToJenreElement(Jenre jenre) {
        return new JenreElement(jenre.getId(), jenre.getFullName());
    }
}
