package ru.otus.pantushev.dto.table;

import lombok.Data;

import java.io.Serializable;

@Data
public class TableDto implements Serializable {
    private final String id;
    private final String keyTypeFlg;
    private final String objEntityName;
}
