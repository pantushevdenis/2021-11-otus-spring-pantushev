package ru.otus.spring.pantushev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
