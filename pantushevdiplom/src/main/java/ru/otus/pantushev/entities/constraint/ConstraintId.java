package ru.otus.pantushev.entities.constraint;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ConstraintId implements Serializable {
    private static final long serialVersionUID = 202586613304824414L;
    @Column(name = "CONST_ID", nullable = false, length = 12)
    private String constId;

    @Column(name = "OWNER_FLG", nullable = false, length = 4)
    private String ownerFlg;

    public String getConstId() {
        return constId;
    }

    public void setConstId(String constId) {
        this.constId = constId;
    }

    public String getOwnerFlg() {
        return ownerFlg;
    }

    public void setOwnerFlg(String ownerFlg) {
        this.ownerFlg = ownerFlg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ConstraintId entity = (ConstraintId) o;
        return Objects.equals(this.constId, entity.constId) &&
            Objects.equals(this.ownerFlg, entity.ownerFlg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(constId, ownerFlg);
    }

}