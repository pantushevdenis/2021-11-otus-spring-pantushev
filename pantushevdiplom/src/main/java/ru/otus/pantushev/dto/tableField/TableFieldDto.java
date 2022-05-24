package ru.otus.pantushev.dto.tableField;

import lombok.Data;

import java.io.Serializable;

@Data
public class TableFieldDto implements Serializable {
    private final String idTblName;
    private final String idFldName;
    private final Integer seqNum;
    private final Boolean requiredSw;
    private final Boolean nullableSw;
    private final Boolean validateSw;
}
