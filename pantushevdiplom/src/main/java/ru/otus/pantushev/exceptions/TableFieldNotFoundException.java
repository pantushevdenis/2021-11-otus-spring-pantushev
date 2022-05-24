package ru.otus.pantushev.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableFieldNotFoundException extends RuntimeException {
    private final String idTblName;
    private final String idFldName;

    public TableFieldNotFoundException(String idTblName, String idFldName) {
        super("TableField not found: " + idTblName + ", " + idFldName);
        this.idTblName = idFldName;
        this.idFldName = idTblName;
    }
}
