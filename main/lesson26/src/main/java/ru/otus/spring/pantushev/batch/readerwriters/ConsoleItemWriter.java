package ru.otus.spring.pantushev.batch.readerwriters;


import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class ConsoleItemWriter<T> implements ItemWriter<T> {
    @Override
    public void write(List<? extends T> list) {
        for(T i : list) {
            System.out.println(i);
        }
    }
}
