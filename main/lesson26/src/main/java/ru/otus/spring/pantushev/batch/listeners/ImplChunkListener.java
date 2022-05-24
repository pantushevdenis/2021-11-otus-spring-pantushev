package ru.otus.spring.pantushev.batch.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;

@Slf4j
public class ImplChunkListener implements ChunkListener {
    @Override
    public void beforeChunk(ChunkContext chunkContext) {
        log.info("Начало пачки");
    }

    @Override
    public void afterChunk(ChunkContext chunkContext) {
        log.info("Конец пачки");
    }

    @Override
    public void afterChunkError(ChunkContext chunkContext) {
        log.info("Ошибка пачки");
    }
}
