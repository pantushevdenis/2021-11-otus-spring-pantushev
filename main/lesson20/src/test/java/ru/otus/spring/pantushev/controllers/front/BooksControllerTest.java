package ru.otus.spring.pantushev.controllers.front;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@DisplayName("Тест фронт-контроллера BooksController")
@ActiveProfiles("unit-test")
public class BooksControllerTest {
    public static final String URI_STRING = "http://localhost:";
    public static final String URI_PAGE_STRING = "/books";

    @LocalServerPort
    private int port;

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void shouldReturnPageAuthors() {
        String body = webTestClient.get().uri(URI_STRING + port + URI_PAGE_STRING)
            .exchange()
            .expectStatus().isOk()
            .expectBody(String.class)
            .returnResult().getResponseBody();
        assertThat(body)
            .isNotBlank();
    }
}
