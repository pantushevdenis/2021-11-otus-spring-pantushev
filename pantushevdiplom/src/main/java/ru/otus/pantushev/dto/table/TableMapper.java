package ru.otus.pantushev.dto.table;

import ru.otus.pantushev.entities.table.Table;

public class TableMapper {
    public static TableDto toDto(Table table) {
        return new TableDto(table.getId(), table.getKeyTypeFlg(), table.getObjEntityName());
    }

    public static Table toEntity(TableDto dto) {
        Table table = new Table(dto.getId());
        table.setKeyTypeFlg(dto.getKeyTypeFlg());
        table.setObjEntityName(dto.getObjEntityName());
        TableDefaultSetter.setDfaultValues(table);
        return table;
    }

    public static Table toEntity(TableDto dto, Table dest) {
        dest.setKeyTypeFlg(dto.getKeyTypeFlg());
        dest.setObjEntityName(dto.getObjEntityName());
        return dest;
    }
}
