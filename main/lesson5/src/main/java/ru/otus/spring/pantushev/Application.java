package ru.otus.spring.pantushev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.pantushev.services.ExamExecutorServiceImpl;

@SpringBootApplication
public class Application {


    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
    }

}
