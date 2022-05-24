package ru.otus.spring.pantushev.controllers.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.otus.spring.pantushev.repositories.AuthorsRepository;
import ru.otus.spring.pantushev.repositories.BooksRepository;



@WebFluxTest(AuthorsRestController.class)
public class AuthorsRestControllerTest {
    @MockBean
    AuthorsRepository authorsRepository;

    @MockBean
    AuthorsRepository jenresRepository;

    @MockBean
    BooksRepository booksRepository;

    @Autowired
    WebTestClient webTestClient;



    @Test
    @DisplayName("Должен отдавать список всех авторов")
    public void shouldGetAuthors() throws Exception {
        webTestClient.get()
            .uri("/api/authors")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk();


    }

}
