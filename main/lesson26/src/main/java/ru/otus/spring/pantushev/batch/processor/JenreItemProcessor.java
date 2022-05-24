package ru.otus.spring.pantushev.batch.processor;

import org.springframework.batch.item.ItemProcessor;
import ru.otus.spring.pantushev.batch.caches.JenreNameCach;
import ru.otus.spring.pantushev.domain.flatfile.BookRead;
import ru.otus.spring.pantushev.domain.mongodb.Jenre;

public class JenreItemProcessor implements ItemProcessor<BookRead, Jenre> {
    private final JenreNameCach jenreNameCach;

    public JenreItemProcessor(JenreNameCach jenreNameCach) {
        this.jenreNameCach = jenreNameCach;
    }


    @Override
    public Jenre process(BookRead bookRead) {
        String jenreName = bookRead.getJenreName();
        if (!jenreNameCach.exists(jenreName)) {
            return new Jenre(jenreName, jenreName);
        }
        else {
            return null;
        }
    }
}
