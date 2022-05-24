package ru.otus.spring.pantushev.controllers.front;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(AuthorsController.class)
@DisplayName("Тест фронт контроллера AuthorsController")
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("unit-test")
public class AuthorsControllerWebMVCTest {
    @Autowired
    MockMvc mockMvc;

    //@MockBean
    //ReactiveMongoTemplate reactiveMongoTemplate;

    @Test
    @DisplayName("Должен возвращать OK на Authors")
    public void shoudlReturnOkForGetAuthors() throws Exception {
        mockMvc.perform(get("/authors"))
            .andExpect(status().isOk());

    }

}

