package ru.otus.pantushev.entities.table;

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
public class TableLangId implements Serializable {
    private static final long serialVersionUID = -902988364915863849L;

    @Column(name = "TBL_NAME", nullable = false, length = 30)
    private String tblName;

    @Column(name = "LANGUAGE_CD", nullable = false, length = 3)
    private String languageCd;
}