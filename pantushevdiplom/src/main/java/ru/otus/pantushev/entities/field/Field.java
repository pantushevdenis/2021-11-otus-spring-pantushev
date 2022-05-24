package ru.otus.pantushev.entities.field;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import ru.otus.pantushev.entities.OracleCharToBooleanConverterNullable;

import javax.persistence.*;
import javax.persistence.Table;

@NoArgsConstructor
@Data
@Entity
@Table(name = "CI_MD_FLD")
public class Field {
    @Id
    @Column(name = "FLD_NAME", nullable = false, length = 30)
    private String id;

    @Column(name = "BASE_FLD_NAME", nullable = false, length = 30)
    private String baseFldName;

    @Column(name = "DATA_TYPE_FLG", nullable = false, length = 4)
    private String dataTypeFlg;

    @Column(name = "EXT_DATA_TYPE_FLG", nullable = false, length = 4)
    private String extDataTypeFlg;

    @Column(name = "SIGNED_SW")
    @Convert(converter = OracleCharToBooleanConverterNullable.class)
    private Boolean signedSw = false;

    @Column(name = "FLD_PRECISION", nullable = false)
    private Integer fldPrecision;

    @Column(name = "FLD_SCALE", nullable = false)
    private Integer fldScale;

    @Column(name = "VERSION", nullable = false)
    private Integer version;

    @Column(name = "FLD_VAL_CB", nullable = false, length = 8)
    private String fldValCb;

    @Column(name = "WORK_FLD_SW", nullable = false)
    private Boolean workFldSw = false;

    @Column(name = "OWNER_FLG", nullable = false, length = 4)
    private String ownerFlg;

    @Column(name = "OBJ_PROPERTY_NAME", nullable = false, length = 60)
    private String objPropertyName;

    @Column(name = "F1_TRANSLATION_CONTEXT", nullable = false, length = 4000)
    private String f1TranslationContext;

    @Column(name = "F1_TRANSLATABLE_FLG", nullable = false, length = 4)
    private String f1TranslatableFlg;

    public Field(String id) {
        this.id = id;
    }
}