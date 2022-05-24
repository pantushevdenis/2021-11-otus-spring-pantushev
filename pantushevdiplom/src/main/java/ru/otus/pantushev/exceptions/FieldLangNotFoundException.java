package ru.otus.pantushev.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldLangNotFoundException extends RuntimeException {
    private final String idFldName;
    private final String idLanguageCd;

    public FieldLangNotFoundException(String idFldName, String idLanguageCd) {
        super("Field not found: " + idFldName +  ", " + idLanguageCd);
        this.idFldName = idFldName;
        this.idLanguageCd = idLanguageCd;

    }
}
