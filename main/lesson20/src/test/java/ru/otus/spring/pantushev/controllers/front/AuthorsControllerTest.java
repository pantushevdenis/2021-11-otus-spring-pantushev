package ru.otus.spring.pantushev.controllers.front;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@DisplayName("Тест фронт-контроллера AuthorsController")
@ActiveProfiles("unit-test")
public class AuthorsControllerTest {
    public static final String URI_STRING = "http://localhost:";
    public static final String URI_PAGE_STRING = "/authors";

    @LocalServerPort
    private int port;

    @Autowired
    WebTestClient webTestClient;

    @Test
    @DisplayName("Должен возвращать Ok при вызове ")
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
