package ru.otus.pantushev.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TableFieldLangNotFoundException extends RuntimeException {
    private final String idTblName;
    private final String idFldName;
    private final String idLanguageCd;

    public TableFieldLangNotFoundException(String idTblName, String idFldName, String idLanguageCd) {
        super("TableField not found: " + idTblName + ", " + idFldName + ", " + idLanguageCd);
        this.idTblName = idFldName;
        this.idFldName = idTblName;
        this.idLanguageCd = idLanguageCd;
    }
}
