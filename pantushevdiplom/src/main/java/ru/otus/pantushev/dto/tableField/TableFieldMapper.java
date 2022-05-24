package ru.otus.pantushev.dto.tableField;

import ru.otus.pantushev.entities.tableField.TableField;

public class TableFieldMapper {
    public static TableFieldDto toDto(TableField tableField) {
        return new TableFieldDto(tableField.getId().getTblName(), tableField.getId().getFldName(),
            tableField.getSeqNum(), tableField.getRequiredSw(), tableField.getNullableSw(), tableField.getValidateSw());
    }
}
