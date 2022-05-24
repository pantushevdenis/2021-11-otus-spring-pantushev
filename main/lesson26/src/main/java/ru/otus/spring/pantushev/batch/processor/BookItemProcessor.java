package ru.otus.spring.pantushev.batch.processor;


import org.springframework.batch.item.ItemProcessor;
import ru.otus.spring.pantushev.batch.exceptions.AuthorNotFoundException;
import ru.otus.spring.pantushev.batch.exceptions.JenreNotFoundException;
import ru.otus.spring.pantushev.domain.mongodb.Author;
import ru.otus.spring.pantushev.domain.mongodb.Jenre;
import ru.otus.spring.pantushev.domain.mongodb.Transform;
import ru.otus.spring.pantushev.domain.mongodb.bookDocument.AuthorElement;
import ru.otus.spring.pantushev.domain.mongodb.bookDocument.Book;
import ru.otus.spring.pantushev.domain.mongodb.bookDocument.JenreElement;
import ru.otus.spring.pantushev.domain.flatfile.BookRead;
import ru.otus.spring.pantushev.repositories.AuthorsRepository;
import ru.otus.spring.pantushev.repositories.JenresRepository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BookItemProcessor implements ItemProcessor<BookRead, Book> {
    private final AuthorsRepository authorsRepository;
    private final JenresRepository jenresRepository;

    private final Map<String, Author> authorsCach = new HashMap<>();
    private final Map<String, Jenre> jenresCach = new HashMap<>();

    public BookItemProcessor(AuthorsRepository authorsRepository, JenresRepository jenresRepository) {
        this.authorsRepository = authorsRepository;
        this.jenresRepository = jenresRepository;
    }

    @Override
    public Book process(BookRead bookRead) throws Exception {
        String authorName = bookRead.getAuthorName();
        String jenreName = bookRead.getJenreName();

        Author author = authorsCach.get(authorName);
        if (author == null) {
            author = authorsRepository.findAuthorByFullName(authorName);
            if (author != null) {
                authorsCach.put(authorName, author);
            }
            else {
                throw new AuthorNotFoundException("Author with name <" + authorName + "> not found in collection Author");
            }
        }

        Jenre jenre = jenresCach.get(jenreName);
        if (jenre == null) {
            jenre = jenresRepository.findJenreByFullName(jenreName);
            if (jenre != null) {
                jenresCach.put(jenreName, jenre);
            }
            else {
                throw new JenreNotFoundException("Jenre with name <" + jenreName + "> not found in collection Jenre");
            }
        }

        AuthorElement authorElement = Transform.authorToAuthorElement(author);
        JenreElement jenreElement = Transform.jenreToJenreElement(jenre);
        Integer publishingYear = Integer.parseInt(bookRead.getPublishingYear());

        return new Book(bookRead.getName(), authorElement, jenreElement, publishingYear);
    }
}
