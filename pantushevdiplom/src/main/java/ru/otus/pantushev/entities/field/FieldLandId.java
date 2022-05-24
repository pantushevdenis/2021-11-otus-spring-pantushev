package ru.otus.pantushev.entities.field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class FieldLandId implements Serializable {
    private static final long serialVersionUID = -1823552560225755539L;

    @Column(name = "FLD_NAME", nullable = false, length = 30)
    private String fldName;

    @Column(name = "LANGUAGE_CD", nullable = false, length = 3)
    private String languageCd;
}