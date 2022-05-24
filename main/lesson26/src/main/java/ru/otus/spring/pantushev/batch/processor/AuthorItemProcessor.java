package ru.otus.spring.pantushev.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import ru.otus.spring.pantushev.batch.caches.AuthorNameCach;
import ru.otus.spring.pantushev.domain.flatfile.BookRead;
import ru.otus.spring.pantushev.domain.mongodb.Author;

public class AuthorItemProcessor implements ItemProcessor<BookRead, Author> {
    private final AuthorNameCach authorNameCach;

    public AuthorItemProcessor(AuthorNameCach authorNameCach) {
        this.authorNameCach = authorNameCach;
    }

    @Override
    public Author process(BookRead bookRead) {
        String authorName = bookRead.getAuthorName();
        if (!authorNameCach.exists(authorName)) {
            return new Author(authorName, authorName, null);
        }
        else {
            return null;
        }
    }
}
