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
import ru.otus.spring.pantushev.batch.caches.JenreNameCach;
import ru.otus.spring.pantushev.batch.listeners.*;
import ru.otus.spring.pantushev.batch.processor.JenreItemProcessor;
import ru.otus.spring.pantushev.batch.readerwriters.ConsoleItemWriter;
import ru.otus.spring.pantushev.domain.flatfile.BookRead;
import ru.otus.spring.pantushev.domain.mongodb.Jenre;
import ru.otus.spring.pantushev.repositories.JenresRepository;

@Configuration
@EnableBatchProcessing
public class JenreImportJobConfiguration {
    protected final JobBuilderFactory jobBuilderFactory;
    protected final StepBuilderFactory stepBuilderFactory;

    public JenreImportJobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public ItemProcessor<BookRead, Jenre> jenreItemProcessor(JenreNameCach jenreNameCach) {
        return new JenreItemProcessor(jenreNameCach);
    }


    @Bean
    public MongoItemWriter<Jenre> mongoJenreWriter(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<Jenre>()
            .template(mongoTemplate)
            .build();
    }

    @Bean
    ConsoleItemWriter<Jenre> consoleJenreWriter() {
        return new ConsoleItemWriter<>();
    }


    @Bean
    public Step transformFlatFileToMongoJenreStep(
        FlatFileItemReader<BookRead> flatFileBookReadReader,
        MongoItemWriter<Jenre> mongoJenreWriter,
        ItemProcessor<BookRead, Jenre> jenreItemProcessor
    ) {
        return stepBuilderFactory.get("transformFlatFileToMongoJenreStep")
            .<BookRead, Jenre>chunk(CommonConsts.CHUNK_SIZE_TO_DICTIONNARY)
            .reader(flatFileBookReadReader)
            .processor(jenreItemProcessor)
            .writer(mongoJenreWriter)
            .listener(new ImplItemReadListener<>())
            .listener(new ImplItemWriteListener<>())
            .listener(new ImplItemProcessListener<>())
            .listener(new ImplChunkListener())
            .build();
    }


    @Bean
    public Step transformFlatFileToConsoleJenreStep(
        FlatFileItemReader<BookRead> flatFileBookReadReader,
        ConsoleItemWriter<Jenre> consoleJenreWriter,
        ItemProcessor<BookRead, Jenre> jenreItemProcessor
    ) {
        return stepBuilderFactory.get("transformFlatFileToConsoleJenreStep")
            .<BookRead, Jenre>chunk(CommonConsts.CHUNK_SIZE_TO_DICTIONNARY)
            .reader(flatFileBookReadReader)
            .processor(jenreItemProcessor)
            .writer(consoleJenreWriter)
            .allowStartIfComplete(true)
            .listener(new ImplItemReadListener<>())
            .listener(new ImplItemWriteListener<>())
            .listener(new ImplItemProcessListener<>())
            .listener(new ImplChunkListener())
            .build();
    }

    @Bean
    public Job importJenres(
        Step transformFlatFileToMongoJenreStep
    ) {
        return jobBuilderFactory.get(CommonConsts.IMPORT_JENRES_JOB_NAME)
            .start(transformFlatFileToMongoJenreStep)
            .listener(new ImplJobExecutionListener())
            .build();
    }

}
