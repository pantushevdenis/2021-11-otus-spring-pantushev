package ru.otus.pantushev.dto.field;


import ru.otus.pantushev.dto.table.TableDefaultSetter;
import ru.otus.pantushev.dto.table.TableDto;
import ru.otus.pantushev.entities.field.Field;
import ru.otus.pantushev.entities.table.Table;

public class FieldMapper {
    public static FieldDto toDto(Field field) {
        return new FieldDto(field.getId(), field.getDataTypeFlg(), field.getSignedSw(), field.getFldPrecision(), field.getFldScale(), field.getObjPropertyName());
    }

    public static Field toEntity(FieldDto dto) {
        Field field = new Field(dto.getId());
        field.setDataTypeFlg(dto.getDataTypeFlg());
        field.setSignedSw(dto.getSignedSw());
        field.setFldPrecision(dto.getFldPrecision());
        field.setFldScale(dto.getFldScale());
        field.setObjPropertyName(dto.getObjPropertyName());

        FieldDefaultSetter.setDfaultValues(field);
        return field;
    }

    public static Field toEntity(FieldDto dto, Field dest) {
        dest.setDataTypeFlg(dto.getDataTypeFlg());
        dest.setSignedSw(dto.getSignedSw());
        dest.setFldPrecision(dto.getFldPrecision());
        dest.setFldScale(dto.getFldScale());
        dest.setObjPropertyName(dto.getObjPropertyName());
        return dest;
    }
}

