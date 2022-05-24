package ru.otus.pantushev.dto.table;

import ru.otus.pantushev.entities.table.TableLang;

public class TableLangDefaultSetter {
    public static void setDefaultValues(TableLang tableLang) {
        tableLang.setVersion(1);
        tableLang.setDescrlong(" ");
        tableLang.setOwnerFlg("CM");
    }
}
