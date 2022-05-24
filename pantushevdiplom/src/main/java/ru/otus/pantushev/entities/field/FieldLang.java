package ru.otus.pantushev.entities.field;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CI_MD_FLD_L")
public class FieldLang {
    @EmbeddedId
    private FieldLandId id;

    @Column(name = "LABEL_LONG", nullable = false, length = 60)
    private String labelLong;

    @Column(name = "VERSION", nullable = false)
    private Integer version;

    @Column(name = "DESCRLONG", nullable = false, length = 4000)
    private String descrlong;

    @Column(name = "OWNER_FLG", nullable = false, length = 4)
    private String ownerFlg;

    @Column(name = "OVRD_LABEL", nullable = false, length = 60)
    private String ovrdLabel;

    @Column(name = "DESCRLONG_OVRD", nullable = false, length = 4000)
    private String descrlongOvrd;

    public FieldLandId getId() {
        return id;
    }

    public void setId(FieldLandId id) {
        this.id = id;
    }

    public String getLabelLong() {
        return labelLong;
    }

    public void setLabelLong(String labelLong) {
        this.labelLong = labelLong;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getDescrlong() {
        return descrlong;
    }

    public void setDescrlong(String descrlong) {
        this.descrlong = descrlong;
    }

    public String getOwnerFlg() {
        return ownerFlg;
    }

    public void setOwnerFlg(String ownerFlg) {
        this.ownerFlg = ownerFlg;
    }

    public String getOvrdLabel() {
        return ovrdLabel;
    }

    public void setOvrdLabel(String ovrdLabel) {
        this.ovrdLabel = ovrdLabel;
    }

    public String getDescrlongOvrd() {
        return descrlongOvrd;
    }

    public void setDescrlongOvrd(String descrlongOvrd) {
        this.descrlongOvrd = descrlongOvrd;
    }

}