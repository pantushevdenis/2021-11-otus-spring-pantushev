package ru.otus.spring.pantushev.batch.configuration;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.spring.pantushev.batch.listeners.ImplJobExecutionListener;

@Configuration
@EnableBatchProcessing
public class AllImportJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;

    public AllImportJobConfiguration(JobBuilderFactory jobBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
    }

    @Bean
    public Job importAll(
        Step transformFlatFileToMongoAuthorStep,
        Step transformFlatFileToMongoJenreStep,
        Step transformFlatFileToMongoBookStep
    ) {
        return jobBuilderFactory.get(CommonConsts.IMPORT_ALL_JOB_NAME)
            .start(transformFlatFileToMongoAuthorStep)
            .next(transformFlatFileToMongoJenreStep)
            .next(transformFlatFileToMongoBookStep)
            .listener(new ImplJobExecutionListener())
            .build();
    }

}
