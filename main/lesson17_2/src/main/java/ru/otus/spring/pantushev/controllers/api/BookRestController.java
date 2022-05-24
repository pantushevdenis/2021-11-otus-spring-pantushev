package ru.otus.spring.pantushev.controllers.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.pantushev.domain.Author;
import ru.otus.spring.pantushev.domain.Book;
import ru.otus.spring.pantushev.domain.Jenre;
import ru.otus.spring.pantushev.dto.BookDto;
import ru.otus.spring.pantushev.dto.BookSaveDto;
import ru.otus.spring.pantushev.exceptions.BookDataWrongException;
import ru.otus.spring.pantushev.exceptions.BookNotFoundException;
import ru.otus.spring.pantushev.exceptions.JenreNotFoundException;
import ru.otus.spring.pantushev.repositories.AuthorsRepository;
import ru.otus.spring.pantushev.repositories.BooksRepository;
import ru.otus.spring.pantushev.repositories.JenresRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController
public class BookRestController {
    private BooksRepository booksRepository;
    private AuthorsRepository authorsRepository;
    private JenresRepository jenresRepository;

    @Autowired
    public BookRestController(BooksRepository booksRepository, AuthorsRepository authorsRepository, JenresRepository jenresRepository) {
        this.booksRepository = booksRepository;
        this.authorsRepository = authorsRepository;
        this.jenresRepository = jenresRepository;
    }

    @GetMapping("/books")
    public List<BookDto> get() {
        List<Book> books = booksRepository.findAll();
        List<BookDto> booksDto = books.stream().map(o -> new BookDto(o)).collect(Collectors.toList());
        return booksDto;
    }

    @GetMapping("/book/{id}")
    public BookDto get(@PathVariable("id") Long id) {
        Book book = booksRepository.findById(id).orElseThrow(NotFoundException::new);
        BookDto booksDto = new BookDto(book);
        return booksDto;
    }

    @DeleteMapping("/book/{id}")
    public void delete(@PathVariable("id") Long id) {
        booksRepository.deleteById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/book")
    public Book postSave(@RequestBody BookSaveDto dto) {
        validateBookData(dto);

        Author author = authorsRepository.findById(dto.getAuthorId()).orElseThrow();
        Jenre jenre = jenresRepository.findById(dto.getJenreId()).orElseThrow();
        Book book = new Book(dto.getName(), author, jenre, dto.getPublishingYear());
        return booksRepository.save(book);
    }

    @PutMapping("/book")
    public BookDto putSaveBook(@RequestBody BookSaveDto dto) {
        if (dto.getId() == 0L) {
            throw new BookDataWrongException("Идентификатор книги не указан!");
        }
        validateBookData(dto);

        Book book = booksRepository.findById(dto.getId()).orElseThrow(() -> {
            return new BookNotFoundException(dto.getId());
        });
        Author author = authorsRepository.findById(dto.getAuthorId()).orElseThrow(() -> {
            return new BookNotFoundException(dto.getAuthorId());
        });
        Jenre jenre = jenresRepository.findById(dto.getJenreId()).orElseThrow(() -> {
            return new JenreNotFoundException(dto.getJenreId());
        });
        book.setName(dto.getName());
        book.setAuthor(author);
        book.setJenre(jenre);
        book.setPublishingYear(dto.getPublishingYear());
        Book bookUpdated = booksRepository.save(book);
        BookDto bookDto = new BookDto(bookUpdated);
        return bookDto;
    }


    private void validateBookData(BookSaveDto dto) {
        if (StringUtils.isEmpty(dto.getName())) {
            throw new BookDataWrongException("Пустое наименование книги!");
        }
        if (dto.getAuthorId() == 0L) {
            throw new BookDataWrongException("Автор не указан!");
        }
        if (dto.getJenreId() == 0L) {
            throw new BookDataWrongException("Жанр не указан!");
        }

    }



}
