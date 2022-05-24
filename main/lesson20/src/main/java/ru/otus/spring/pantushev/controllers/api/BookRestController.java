package ru.otus.spring.pantushev.controllers.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.pantushev.domain.bookDocument.AuthorElement;
import ru.otus.spring.pantushev.domain.bookDocument.Book;
import ru.otus.spring.pantushev.domain.bookDocument.JenreElement;
import ru.otus.spring.pantushev.dto.BookSaveDto;
import ru.otus.spring.pantushev.exceptions.AuthorNotFoundException;
import ru.otus.spring.pantushev.exceptions.BookDataWrongException;
import ru.otus.spring.pantushev.exceptions.JenreNotFoundException;
import ru.otus.spring.pantushev.repositories.AuthorsRepository;
import ru.otus.spring.pantushev.repositories.BooksRepository;
import ru.otus.spring.pantushev.repositories.JenresRepository;

@RequestMapping("/api")
@RestController
public class BookRestController {
    private final BooksRepository booksRepository;
    private final AuthorsRepository authorsRepository;
    private final JenresRepository jenresRepository;

    @Autowired
    public BookRestController(BooksRepository booksRepository, AuthorsRepository authorsRepository, JenresRepository jenresRepository) {
        this.booksRepository = booksRepository;
        this.authorsRepository = authorsRepository;
        this.jenresRepository = jenresRepository;
    }

    @GetMapping("/books")
    public Flux<Book> get() {
        Flux<Book> books = booksRepository.findAll();
        return books;
    }

    @GetMapping("/book/{id}")
    public Mono<ResponseEntity<Book>> get(@PathVariable("id") String id) {
        Mono<Book> book = booksRepository.findById(id);
        return book
            .map(b -> new ResponseEntity<>(b, HttpStatus.OK))
            .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/book")
    public Mono<ResponseEntity<Book>> postSave(@RequestBody BookSaveDto dto) {
        validateBookData(dto);

        return Mono.just(new Book())
            .map(b -> {
                b.setName(dto.getName());
                b.setPublishingYear(dto.getPublishingYear());
                return b;
            })
            .flatMap(b -> {
                return authorsRepository.findById(dto.getAuthorId())
                    .map(a -> {
                        b.setAuthor(new AuthorElement(a));
                        return b;
                    });
            })
            .flatMap(b -> {
                return jenresRepository.findById(dto.getJenreId())
                    .map(j -> {
                        b.setJenre(new JenreElement(j));
                        return b;
                    });
            })
            .flatMap(b -> {
                return booksRepository.save(b)
                    .map(b2 -> new ResponseEntity<>(b2, HttpStatus.CREATED));
            })
            .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/book/{id}")
    public Mono<Void> delete(@PathVariable("id") String id) {
        return booksRepository.deleteById(id);
    }

    @PutMapping("/book")
    public Mono<ResponseEntity<Book>> putSaveBook(@RequestBody BookSaveDto dto) {
        if (StringUtils.isEmpty(dto.getId())) {
            throw new BookDataWrongException("Идентификатор книги не указан!");
        }
        validateBookData(dto);

        return booksRepository.findById(dto.getId())
            .map(b -> {
                b.setName(dto.getName());
                b.setPublishingYear(dto.getPublishingYear());
                return b;
            })
            .flatMap(b -> {
                return authorsRepository.findById(dto.getAuthorId())
                    .map(a -> {
                        b.setAuthor(new AuthorElement(a));
                        return b;
                    })
                    .switchIfEmpty(Mono.error(new AuthorNotFoundException(dto.getAuthorId())));
            })
            .flatMap(b -> {
                return jenresRepository.findById(dto.getJenreId())
                    .map(j -> {
                        b.setJenre(new JenreElement(j));
                        return b;
                    })
                    .switchIfEmpty(Mono.error(new JenreNotFoundException(dto.getAuthorId())));
            })
            .flatMap(b -> {
                return booksRepository.save(b)
                    .map(b2 -> new ResponseEntity<>(b2, HttpStatus.ACCEPTED));
            })
            .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    private void validateBookData(BookSaveDto dto) {
        if (StringUtils.isEmpty(dto.getName())) {
            throw new BookDataWrongException("Пустое наименование книги!");
        }
        if (StringUtils.isEmpty(dto.getAuthorId())) {
            throw new BookDataWrongException("Автор не указан!");
        }
        if (StringUtils.isEmpty(dto.getJenreId())) {
            throw new BookDataWrongException("Жанр не указан!");
        }
    }



}
