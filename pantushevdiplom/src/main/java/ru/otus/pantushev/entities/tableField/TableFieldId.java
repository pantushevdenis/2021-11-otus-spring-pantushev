package ru.otus.pantushev.entities.tableField;

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
public class TableFieldId implements Serializable {
    private static final long serialVersionUID = -3831960089055073332L;

    @Column(name = "TBL_NAME", nullable = false, length = 30)
    private String tblName;

    @Column(name = "FLD_NAME", nullable = false, length = 30)
    private String fldName;
}