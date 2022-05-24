package ru.otus.spring.pantushev.controllers.front;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("Тестирование работоспособности фронт-контроллеров")
@ActiveProfiles("unit-test")
public class ControllersInitTest {
    @Autowired
    IndexController indexController;

    @Autowired
    AuthorsController authorsController;

    @Autowired
    JenresController jenresController;

    @Autowired
    BooksController booksController;

    @Test
    @DisplayName("Должен подняться бин контроллера indexController")
    public void shouldInitInitController() {
        assertThat(indexController)
            .isNotNull();
    }

    @Test
    @DisplayName("Должен подняться бин контроллера authorsController")
    public void shouldInitAuthorsController() {
        assertThat(authorsController)
            .isNotNull();
    }

    @Test
    @DisplayName("Должен подняться бин контроллера jenresController")
    public void shouldInitJenresController() {
        assertThat(jenresController)
            .isNotNull();
    }

    @Test
    @DisplayName("Должен подняться бин контроллера bookController")
    public void shouldInitBookController() {
        assertThat(booksController)
            .isNotNull();
    }
}
