package ru.otus.pantushev.dto.tableField;

import lombok.Data;

import java.io.Serializable;

@Data
public class TableFieldLangDto implements Serializable {
    private final String idTblName;
    private final String idFldName;
    private final String idLanguageCd;
    private final String labelLong;
}
