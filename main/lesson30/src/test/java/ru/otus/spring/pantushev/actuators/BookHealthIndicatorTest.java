package ru.otus.spring.pantushev.actuators;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({
    //"test", -- problem: No data scripts found at location 'testdata.sql'
    "test-actuator"
})
public class BookHealthIndicatorTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Должен вернуть результаты актуатора")
    void shouldReturnResultActuator() throws Exception {
        mockMvc.perform(get("/health/book"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").exists())
            .andExpect(jsonPath("$.details.BookCount").exists())
            .andExpect(jsonPath("$.details.BookCount").value(4)) // из dev-скрипта
            ;
    }

}
