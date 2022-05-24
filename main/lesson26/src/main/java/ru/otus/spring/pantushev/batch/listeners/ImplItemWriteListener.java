package ru.otus.spring.pantushev.batch.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

@Slf4j
public class ImplItemWriteListener<T> implements ItemWriteListener<T> {
    @Override
    public void beforeWrite(List<? extends T> list) {
        log.info("Начало записи");
    }

    @Override
    public void afterWrite(List<? extends T> list) {
        log.info("Конец записи");
    }

    @Override
    public void onWriteError(Exception e, List<? extends T> list) {
        log.info("Ошибка записи");
    }
}
