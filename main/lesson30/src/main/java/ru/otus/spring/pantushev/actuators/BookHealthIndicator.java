package ru.otus.spring.pantushev.actuators;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ru.otus.spring.pantushev.repositories.BooksRepository;

@Component("book")
public class BookHealthIndicator implements HealthIndicator {
    private final BooksRepository booksRepository;

    public BookHealthIndicator(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    public Health health() {
        long bookCount = booksRepository.count();

        Health.Builder healthBuilder = Health.up()
            .withDetail("BookCount", bookCount);

        return healthBuilder.build();
    }
}
