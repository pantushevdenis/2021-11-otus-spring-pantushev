package ru.otus.spring.pantushev.batch.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

@Slf4j
public class ImplJobExecutionListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Начало выполнения задания");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("Окончание задания importOnlyBooks");
    }
}
