package ru.otus.spring.pantushev.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Jenre
        implements Printable
{
    private final long id;
    private final String name;

    //@Override
    public static String getHead() {
        return bar +
                "ID\tName\n" +
                bar;
    }

    @Override
    public String getLine() {
        return String.format("%d\t%s\n", this.id, this.name);
    }

    private static final String bar = "----------------------------------------------------\n";

}
