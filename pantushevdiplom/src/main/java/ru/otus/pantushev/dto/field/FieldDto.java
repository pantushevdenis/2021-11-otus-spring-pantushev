package ru.otus.pantushev.dto.field;

import lombok.Data;

import java.io.Serializable;

@Data
public class FieldDto implements Serializable {
    private final String id;
    private final String dataTypeFlg;
    private final Boolean signedSw;
    private final Integer fldPrecision;
    private final Integer fldScale;
    private final String objPropertyName;
}
