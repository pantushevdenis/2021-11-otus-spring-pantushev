package ru.otus.pantushev.dto.field;

import lombok.Data;

import java.io.Serializable;

@Data
public class FieldLangDto implements Serializable {
    private final String idFldName;
    private final String idLanguageCd;
    private final String labelLong;
}
