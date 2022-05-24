package ru.otus.pantushev.dto.table;

import ru.otus.pantushev.entities.table.TableLang;
import ru.otus.pantushev.entities.table.TableLangId;

public class TableLangMapper {
    public static TableLangDto toDto(TableLang tableLang) {
        return new TableLangDto(tableLang.getId().getTblName(), tableLang.getId().getLanguageCd(), tableLang.getDescr());
    }

    public static TableLang toEntity(TableLangDto dto) {
        TableLang tableLang = new TableLang(new TableLangId(dto.getTableName(), dto.getLanguageCd()));
        TableLangDefaultSetter.setDefaultValues(tableLang);
        tableLang.setDescr(dto.getDescr());
        return tableLang;
    }

    public static TableLang toEntity(TableLangDto dto, TableLang dest) {
        dest.setDescr(dto.getDescr());
        return dest;
    }
}
