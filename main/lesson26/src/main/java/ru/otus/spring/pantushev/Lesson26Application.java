package ru.otus.spring.pantushev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

//@SpringBootApplication

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class Lesson26Application {

	public static void main(String[] args) {
		SpringApplication.run(Lesson26Application.class, args);
	}

}
