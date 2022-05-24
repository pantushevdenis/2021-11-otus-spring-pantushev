package ru.otus.spring.pantushev.repositories;

import org.apache.commons.lang3.time.DateUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import ru.otus.spring.pantushev.domain.Author;
import ru.otus.spring.pantushev.domain.Jenre;
import ru.otus.spring.pantushev.domain.bookDocument.AuthorElement;
import ru.otus.spring.pantushev.domain.bookDocument.Book;
import ru.otus.spring.pantushev.domain.bookDocument.JenreElement;
import ru.otus.spring.pantushev.dto.dropdownLists.AuthorDto;
import ru.otus.spring.pantushev.dto.dropdownLists.JenreDto;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Authors repository")
@DataMongoTest
@ActiveProfiles("unit-test")
public class RepositoryTest {

    @Autowired
    private AuthorsRepository authorsRepository;

    @Autowired
    private JenresRepository jenresRepository;

    @Autowired
    private BooksRepository booksRepository;

    private final List<Jenre> jenres = new ArrayList<>();
    private final List<Author> authors = new ArrayList<>();
    private final List<Book> books = new ArrayList<>();

    {
        try {
            jenres.add(new Jenre("Jenre1 fullname", "Jenre1"));
            jenres.add(new Jenre("Jenre2 fullname", "Jenre2"));

            authors.add(new Author("Author1 fullname", "Author1", DateUtils.parseDate("01-01-2001", "dd-mm-yyyy")));
            authors.add(new Author("Author2 fullname", "Author2", DateUtils.parseDate("01-01-2022", "dd-mm-yyyy")));

            books.add(new Book("Book1",
                new AuthorElement(authors.get(0)),
                new JenreElement(jenres.get(0)),
                2003));
            books.add(new Book("Book2",
                new AuthorElement(authors.get(1)),
                new JenreElement(jenres.get(0)),
                2004));
            books.add(new Book("Book3",
                new AuthorElement(authors.get(1)),
                new JenreElement(jenres.get(1)),
                2005));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    @BeforeEach
    private void initDb() {
        authorsRepository.deleteAll().block();
        jenresRepository.deleteAll().block();
        booksRepository.deleteAll().block();

        jenresRepository.insert(jenres).subscribe();
        authorsRepository.insert(authors).subscribe();
        booksRepository.insert(books).subscribe();
    }

    @DisplayName("Должен внедрять репозиторий авторов")
    @Test
    public void shouldAutowiredAuthorsRepository() {
        assertNotNull(authorsRepository);
    }

    @DisplayName("Должен внедрять репозиторий жанров")
    @Test
    public void shouldAutowiredJenresRepository() {
        assertNotNull(jenresRepository);
    }

    @DisplayName("Должен внедрять репозиторий книг")
    @Test
    public void shouldAutowiredBooksRepository() {
        assertNotNull(booksRepository);
    }

    @DisplayName("Вывести всех авторов")
    @Test
    public void shouldFindAllAuthors() {
        List<Author> actual = authorsRepository.findAll()
            .toStream().collect(Collectors.toList());
        Assertions.assertThat(actual)
            .isNotNull()
            .hasSize(2);
    }

    @DisplayName("Вывести всех авторов в dropdownList")
    @Test
    public void shoilFindAllAuthorsDropodownList() {
        List<AuthorDto> actual = authorsRepository.findAllDropdownList()
            .toStream().collect(Collectors.toList());
        Assertions.assertThat(actual)
            .isNotNull()
            .hasSize(2);
        AuthorDto actual1 = actual.get(0);
        Assertions.assertThat(actual1.getShortName())
            .isEqualTo("Author1");
        AuthorDto actual2 = actual.get(1);
        Assertions.assertThat(actual2.getShortName())
            .isEqualTo("Author2");
    }

    @DisplayName("Вывести все жанры")
    @Test
    public void shouldFindAllJenres() {
        List<Jenre> actual = jenresRepository.findAll()
            .toStream().collect(Collectors.toList());
        Assertions.assertThat(actual)
            .isNotNull()
            .hasSize(2);
    }

    @DisplayName("Вывести все жанры в dropdownLIst")
    @Test
    public void shouldFindAllJenresDropDownList() {
        List<JenreDto> actual = jenresRepository.findAllDropdownList()
            .toStream().collect(Collectors.toList());
        Assertions.assertThat(actual)
            .isNotNull()
            .hasSize(2);
        JenreDto actual1 = actual.get(0);
        JenreDto actual2 = actual.get(1);
        Assertions.assertThat(actual1.getShortName())
            .isEqualTo("Jenre1");
        Assertions.assertThat(actual2.getShortName())
            .isEqualTo("Jenre2");
    }

    @DisplayName("Вывести все книги")
    @Test
    public void shouldFindAllBooks() {
        List<Book> actual = booksRepository.findAll()
            .toStream().collect(Collectors.toList());
        Assertions.assertThat(actual)
            .isNotNull()
            .hasSize(3);
    }


}
