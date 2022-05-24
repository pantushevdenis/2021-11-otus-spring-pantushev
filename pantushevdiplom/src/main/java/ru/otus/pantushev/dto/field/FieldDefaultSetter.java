package ru.otus.pantushev.dto.field;

import ru.otus.pantushev.entities.field.Field;
import ru.otus.pantushev.entities.table.Table;

public class FieldDefaultSetter {
    public static void setDfaultValues(Field field) {
        field.setBaseFldName(" ");
        field.setExtDataTypeFlg(" ");
        field.setVersion(1);
        field.setFldValCb(" ");
        field.setWorkFldSw(false);
        field.setOwnerFlg("CM");
        field.setF1TranslationContext(" ");
        field.setF1TranslatableFlg("F1YS");
    }
}
