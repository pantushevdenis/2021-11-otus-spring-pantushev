package ru.otus.spring.pantushev.batch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.spring.pantushev.batch.exceptions.AuthorNotFoundException;
import ru.otus.spring.pantushev.batch.exceptions.EntityNotFoundException;
import ru.otus.spring.pantushev.batch.exceptions.JenreNotFoundException;
import ru.otus.spring.pantushev.batch.listeners.*;
import ru.otus.spring.pantushev.batch.processor.BookItemProcessor;
import ru.otus.spring.pantushev.batch.readerwriters.ConsoleItemWriter;
import ru.otus.spring.pantushev.domain.flatfile.BookRead;
import ru.otus.spring.pantushev.domain.mongodb.bookDocument.Book;
import ru.otus.spring.pantushev.repositories.AuthorsRepository;
import ru.otus.spring.pantushev.repositories.JenresRepository;


@Configuration
@EnableBatchProcessing
public class BookImportJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final AuthorsRepository authorsRepository;
    private final JenresRepository jenresRepository;

    public BookImportJobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, AuthorsRepository authorsRepository, JenresRepository jenresRepository) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.authorsRepository = authorsRepository;
        this.jenresRepository = jenresRepository;
    }

    @Bean
    public ItemProcessor<BookRead, Book> bookItemProcessor() {
        return new BookItemProcessor(authorsRepository, jenresRepository);
    }

    @Bean
    ConsoleItemWriter<Book> consoleBookWriter() {
        return new ConsoleItemWriter<>();
    }

    @Bean
    public MongoItemWriter<Book> mongoBookWriter(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<Book>()
            .template(mongoTemplate)
            .build();
    }


    @Bean
    public Step transformFlatFileToMongoBookStep(
        FlatFileItemReader<BookRead> flatFileBookReadReader,
        MongoItemWriter<Book> mongoBookWriter,
        ItemProcessor<BookRead, Book> bookItemProcessor
    ) {
        return stepBuilderFactory.get("transformFlatFileToMongoBookStep")
            .<BookRead, Book>chunk(4)
            .reader(flatFileBookReadReader)
            .processor(bookItemProcessor)
            .writer(mongoBookWriter)
            .faultTolerant()
            .skip(EntityNotFoundException.class)
            .skipPolicy((throwable, i) -> true)
            .listener(new ImplItemReadListener<>())
            .listener(new ImplItemWriteListener<>())
            .listener(new ImplItemProcessListener<>())
            .listener(new ImplChunkListener())
            .build();
    }


    @Bean
    public Step transformFlatFileToConsoleBookStep(
        FlatFileItemReader<BookRead> flatFileBookReadReader,
        ConsoleItemWriter<Book> consoleBookWriter,
        ItemProcessor<BookRead, Book> bookItemProcessor
    ) {
        return stepBuilderFactory.get("transformFlatFileToConsoleBookStep")
            .<BookRead, Book>chunk(CommonConsts.CHUNK_SIZE_TO_OPERATE)
            .reader(flatFileBookReadReader)
            .processor(bookItemProcessor)
            .writer(consoleBookWriter)
            .faultTolerant()
            .skip(AuthorNotFoundException.class)
            .skip(JenreNotFoundException.class)
            .listener(new ImplItemReadListener<>())
            .listener(new ImplItemWriteListener<>())
            .listener(new ImplItemProcessListener<>())
            .listener(new ImplChunkListener())
            .build();
    }


    @Bean
    public Job importBooks(Step transformFlatFileToMongoBookStep) {
        return jobBuilderFactory.get(CommonConsts.IMPORT_BOOKS_JOB_NAME)
            .start(transformFlatFileToMongoBookStep)
            .listener(new ImplJobExecutionListener())
            .build();
    }
}
