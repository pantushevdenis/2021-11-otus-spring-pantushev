package ru.otus.spring.pantushev.batch.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;

@Slf4j
public class ImplItemReadListener<T> implements ItemReadListener<T> {
    @Override
    public void beforeRead() {
        log.info("Начало чтения");
    }

    @Override
    public void afterRead(T t) {
        log.info("Конец чтения: " + t);
    }

    @Override
    public void onReadError(Exception e) {
        log.info("Ошибка чтения");
    }
}
