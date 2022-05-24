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
import ru.otus.spring.pantushev.batch.caches.AuthorNameCach;
import ru.otus.spring.pantushev.batch.listeners.*;
import ru.otus.spring.pantushev.batch.processor.AuthorItemProcessor;
import ru.otus.spring.pantushev.batch.readerwriters.ConsoleItemWriter;
import ru.otus.spring.pantushev.domain.flatfile.BookRead;
import ru.otus.spring.pantushev.domain.mongodb.Author;
import ru.otus.spring.pantushev.repositories.AuthorsRepository;

@Configuration
@EnableBatchProcessing
public class AuthorImportJobConfiguration {
    protected final JobBuilderFactory jobBuilderFactory;
    protected final StepBuilderFactory stepBuilderFactory;

    public AuthorImportJobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public ItemProcessor<BookRead, Author> authorItemProcessor(AuthorNameCach authorNameCach) {
        return new AuthorItemProcessor(authorNameCach);
    }


    @Bean
    public MongoItemWriter<Author> mongoAuthorWriter(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<Author>()
            .template(mongoTemplate)
            .build();
    }

    @Bean
    ConsoleItemWriter<Author> consoleAuthorWriter() {
        return new ConsoleItemWriter<>();
    }


    @Bean
    public Step transformFlatFileToMongoAuthorStep(
        FlatFileItemReader<BookRead> flatFileBookReadReader,
        MongoItemWriter<Author> mongoAuthorWriter,
        ItemProcessor<BookRead, Author> authorItemProcessor
    ) {
        return stepBuilderFactory.get("transformFlatFileToMongoAuthorStep")
            .<BookRead, Author>chunk(4)
            .reader(flatFileBookReadReader)
            .processor(authorItemProcessor)
            .writer(mongoAuthorWriter)
            .listener(new ImplItemReadListener<>())
            .listener(new ImplItemWriteListener<>())
            .listener(new ImplItemProcessListener<>())
            .listener(new ImplChunkListener())
            .build();
    }


    @Bean
    public Step transformFlatFileToConsoleAuthorStep(
        FlatFileItemReader<BookRead> flatFileBookReadReader,
        ConsoleItemWriter<Author> consoleAuthorWriter,
        ItemProcessor<BookRead, Author> authorItemProcessor
    ) {
        return stepBuilderFactory.get("transformFlatFileToConsoleAuthorStep")
            .<BookRead, Author>chunk(CommonConsts.CHUNK_SIZE_TO_DICTIONNARY)
            .reader(flatFileBookReadReader)
            .processor(authorItemProcessor)
            .writer(consoleAuthorWriter)
            .allowStartIfComplete(true)
            .listener(new ImplItemReadListener<>())
            .listener(new ImplItemWriteListener<>())
            .listener(new ImplItemProcessListener<>())
            .listener(new ImplChunkListener())
            .build();
    }

    @Bean
    public Job importAuthors(
        Step transformFlatFileToMongoAuthorStep
    ) {
        return jobBuilderFactory.get(CommonConsts.IMPORT_AUTHORS_JOB_NAME)
            .start(transformFlatFileToMongoAuthorStep)
            .listener(new ImplJobExecutionListener())
            .build();
    }

}
