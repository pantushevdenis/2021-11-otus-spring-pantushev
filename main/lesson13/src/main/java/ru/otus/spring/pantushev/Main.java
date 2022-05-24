package ru.otus.spring.pantushev;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import ru.otus.spring.pantushev.repositories.AuthorsRepository;
import ru.otus.spring.pantushev.repositories.BooksRepository;
import ru.otus.spring.pantushev.repositories.JenresRepository;
import ru.otus.spring.pantushev.services.data.BookServiceImpl;
import ru.otus.spring.pantushev.services.printTable.PrintTableServiceSys;

@EnableMongock
@SpringBootApplication
public class Main {

	private static ApplicationContext context;


	public static void main(String[] args) {
		context = SpringApplication.run(Main.class, args);

		final JenresRepository jr = context.getBean(JenresRepository.class);
		final AuthorsRepository ar = context.getBean(AuthorsRepository.class);
		final BooksRepository br = context.getBean(BooksRepository.class);
		final PrintTableServiceSys pts = context.getBean(PrintTableServiceSys.class);
		final BookServiceImpl cds = context.getBean(BookServiceImpl.class);
	}

	@EventListener(ApplicationStartedEvent.class)
	public void ApplicationStartedEvent() {
		System.out.println("ApplicationStartedEvent!!");
	}

}
