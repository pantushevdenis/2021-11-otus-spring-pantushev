package ru.otus.spring.pantushev;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@DisplayName("Тест поднятия контекста")
@ActiveProfiles("unit-test")
class Lesson17ApplicationTests {


    @Test
	void contextLoads() {
	}


}
