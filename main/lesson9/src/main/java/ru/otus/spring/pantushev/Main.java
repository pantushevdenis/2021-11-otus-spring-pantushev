package ru.otus.spring.pantushev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.h2.tools.Console;

import java.sql.SQLException;

@SpringBootApplication
public class Main {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(Main.class, args);

		Console.main(args);
	}

}
