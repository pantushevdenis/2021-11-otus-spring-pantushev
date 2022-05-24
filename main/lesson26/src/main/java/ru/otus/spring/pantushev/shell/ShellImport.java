package ru.otus.spring.pantushev.shell;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ShellImport {
    private final JobLauncher jobLauncher;
    private final Job importBooks;
    private final Job importAll;
    private final Job importAuthors;
    private final Job importJenres;

    public ShellImport(JobLauncher jobLauncher, Job importBooks, Job importAll, Job importAuthors, Job importJenres) {
        this.jobLauncher = jobLauncher;
        this.importBooks = importBooks;
        this.importAll = importAll;
        this.importAuthors = importAuthors;
        this.importJenres = importJenres;
    }

    @ShellMethod("Import books")
    public void importBooks() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobExecution execution = jobLauncher.run(importBooks, new JobParametersBuilder().toJobParameters());
    }

    @ShellMethod("Import all")
    public void importAll() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobExecution execution = jobLauncher.run(importAll, new JobParametersBuilder().toJobParameters());
    }


    @ShellMethod("Import authors")
    public void importAuthors() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobExecution execution = jobLauncher.run(importAuthors, new JobParametersBuilder().toJobParameters());
    }


    @ShellMethod("Import jenres")
    public void importJenres() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobExecution execution = jobLauncher.run(importJenres, new JobParametersBuilder().toJobParameters());
    }

}
