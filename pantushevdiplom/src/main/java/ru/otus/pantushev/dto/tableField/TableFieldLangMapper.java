package ru.otus.pantushev.dto.tableField;

import ru.otus.pantushev.entities.tableField.TableFieldLang;

public class TableFieldLangMapper {
    public static TableFieldLangDto toDto(TableFieldLang tableFieldLang) {
        return new TableFieldLangDto(tableFieldLang.getId().getTblName(), tableFieldLang.getId().getFldName(),
            tableFieldLang.getId().getLanguageCd(), tableFieldLang.getLabelLong());
    }
}
