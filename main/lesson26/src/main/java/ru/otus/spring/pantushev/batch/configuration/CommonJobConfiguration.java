package ru.otus.spring.pantushev.batch.configuration;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import ru.otus.spring.pantushev.batch.caches.AuthorNameCach;
import ru.otus.spring.pantushev.batch.caches.JenreNameCach;
import ru.otus.spring.pantushev.domain.flatfile.BookRead;

@Configuration
@EnableBatchProcessing
public class CommonJobConfiguration {
    protected final StepBuilderFactory stepBuilderFactory;

    public CommonJobConfiguration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public FlatFileItemReader<BookRead> flatFileBookReadReader() {
        return new FlatFileItemReaderBuilder<BookRead>()
            .name("flatFileBookReadReader")
            .resource(new FileSystemResource(CommonConsts.INPUT_FILE_NAME))
            .linesToSkip(CommonConsts.LINES_TO_SKIP)
            .lineMapper((s, i) -> {
                String[] fieldsValues = s.split(CommonConsts.SPLIT_DELIMITER);
                return new BookRead(fieldsValues[0], fieldsValues[1], fieldsValues[2], fieldsValues[3]);
            })
            .build();
    }
}
