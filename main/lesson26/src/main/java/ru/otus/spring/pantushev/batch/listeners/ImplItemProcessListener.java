package ru.otus.spring.pantushev.batch.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemProcessListener;

@Slf4j
public class ImplItemProcessListener<T, S> implements ItemProcessListener<T, S> {
    @Override
    public void beforeProcess(Object o) {
        log.info("Начало обработки: " + o);
    }

    @Override
    public void afterProcess(Object o, Object o2) {
        log.info("Конец обработки: " + o + " -> " + o2);
    }

    @Override
    public void onProcessError(Object o, Exception e) {
        log.info("Ошибка обработки: " + o + ": " + e);
    }
}
