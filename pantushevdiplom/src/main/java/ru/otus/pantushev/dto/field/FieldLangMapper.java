package ru.otus.pantushev.dto.field;

import ru.otus.pantushev.entities.field.FieldLang;

public class FieldLangMapper {
    public static FieldLangDto toDto(FieldLang fieldLang) {
        return new FieldLangDto(fieldLang.getId().getFldName(), fieldLang.getId().getLanguageCd(), fieldLang.getLabelLong());
    }
}
