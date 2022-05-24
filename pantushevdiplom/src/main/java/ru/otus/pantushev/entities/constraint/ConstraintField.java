package ru.otus.pantushev.entities.constraint;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CI_MD_CONST_FLD")
public class ConstraintField {
    @EmbeddedId
    private ConstraintFieldId id;

    @Column(name = "FLD_NAME", nullable = false, length = 30)
    private String fldName;

    @Column(name = "VERSION", nullable = false)
    private Integer version;

    public ConstraintFieldId getId() {
        return id;
    }

    public void setId(ConstraintFieldId id) {
        this.id = id;
    }

    public String getFldName() {
        return fldName;
    }

    public void setFldName(String fldName) {
        this.fldName = fldName;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}