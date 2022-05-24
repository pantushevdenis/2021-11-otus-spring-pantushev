package ru.otus.pantushev.dto.table;

import lombok.Data;

import java.io.Serializable;

@Data
public class TableLangDto implements Serializable {
    private final String tableName;
    private final String languageCd;
    private final String descr;
}
