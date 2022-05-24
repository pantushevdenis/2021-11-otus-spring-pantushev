package ru.otus.pantushev.entities;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.Converter;
import javax.persistence.AttributeConverter;

@Converter
public class OracleCharToBooleanConverterNullable implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean value) {
        if (value == null)
            return " ";
        else
            return value ? "Y" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String value) {
        if (StringUtils.isBlank(value))
            return null;
        else
            return "Y".equals(value);
    }
}
