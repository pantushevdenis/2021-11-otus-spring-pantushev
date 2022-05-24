package ru.otus.pantushev.entities.constraint;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CI_MD_CONST")
public class Constraint {
    @EmbeddedId
    private ConstraintId id;

    @Column(name = "TBL_NAME", nullable = false, length = 30)
    private String tblName;

    @Column(name = "REF_CONST_OWNER", nullable = false, length = 4)
    private String refConstOwner;

    @Column(name = "REF_CONST_ID", nullable = false, length = 12)
    private String refConstId;

    @Column(name = "CONST_TYPE_FLG", nullable = false, length = 4)
    private String constTypeFlg;

    @Column(name = "ENABLE_RI_SW", nullable = false)
    private Boolean enableRiSw = false;

    @Column(name = "VERSION", nullable = false)
    private Integer version;

    @Column(name = "ADD_COND_SQL_TXT", nullable = false, length = 2000)
    private String addCondSqlTxt;

    public ConstraintId getId() {
        return id;
    }

    public void setId(ConstraintId id) {
        this.id = id;
    }

    public String getTblName() {
        return tblName;
    }

    public void setTblName(String tblName) {
        this.tblName = tblName;
    }

    public String getRefConstOwner() {
        return refConstOwner;
    }

    public void setRefConstOwner(String refConstOwner) {
        this.refConstOwner = refConstOwner;
    }

    public String getRefConstId() {
        return refConstId;
    }

    public void setRefConstId(String refConstId) {
        this.refConstId = refConstId;
    }

    public String getConstTypeFlg() {
        return constTypeFlg;
    }

    public void setConstTypeFlg(String constTypeFlg) {
        this.constTypeFlg = constTypeFlg;
    }

    public Boolean getEnableRiSw() {
        return enableRiSw;
    }

    public void setEnableRiSw(Boolean enableRiSw) {
        this.enableRiSw = enableRiSw;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getAddCondSqlTxt() {
        return addCondSqlTxt;
    }

    public void setAddCondSqlTxt(String addCondSqlTxt) {
        this.addCondSqlTxt = addCondSqlTxt;
    }

}