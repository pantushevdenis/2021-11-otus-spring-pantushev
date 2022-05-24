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
public class TableFieldLangId implements Serializable {
    private static final long serialVersionUID = 5788090807912271676L;
    @Column(name = "TBL_NAME", nullable = false, length = 30)
    private String tblName;

    @Column(name = "FLD_NAME", nullable = false, length = 30)
    private String fldName;

    @Column(name = "LANGUAGE_CD", nullable = false, length = 3)
    private String languageCd;

    public String getTblName() {
        return tblName;
    }

    public void setTblName(String tblName) {
        this.tblName = tblName;
    }

    public String getFldName() {
        return fldName;
    }

    public void setFldName(String fldName) {
        this.fldName = fldName;
    }

    public String getLanguageCd() {
        return languageCd;
    }

    public void setLanguageCd(String languageCd) {
        this.languageCd = languageCd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TableFieldLangId entity = (TableFieldLangId) o;
        return Objects.equals(this.languageCd, entity.languageCd) &&
            Objects.equals(this.fldName, entity.fldName) &&
            Objects.equals(this.tblName, entity.tblName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(languageCd, fldName, tblName);
    }

}