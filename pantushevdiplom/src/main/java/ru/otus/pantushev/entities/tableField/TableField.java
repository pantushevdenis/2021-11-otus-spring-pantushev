package ru.otus.pantushev.entities.tableField;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CI_MD_TBL_FLD")
public class TableField {
    @EmbeddedId
    private TableFieldId id;

    @Column(name = "SEQ_NUM", nullable = false)
    private Integer seqNum;

    @Column(name = "REQUIRED_SW", nullable = false)
    private Boolean requiredSw = false;

    @Column(name = "NULLABLE_SW", nullable = false)
    private Boolean nullableSw = false;

    @Column(name = "VERSION", nullable = false)
    private Integer version;

    @Column(name = "VALIDATE_SW", nullable = false)
    private Boolean validateSw = false;

    @Column(name = "AUDIT_INSERT_SW", nullable = false)
    private Boolean auditInsertSw = false;

    @Column(name = "AUDIT_UPDATE_SW", nullable = false)
    private Boolean auditUpdateSw = false;

    @Column(name = "AUDIT_DELETE_SW", nullable = false)
    private Boolean auditDeleteSw = false;

    @Column(name = "ALLOW_CUSTOM_SW", nullable = false)
    private Boolean allowCustomSw = false;

    @Column(name = "STD_TM_SRC_FLG", nullable = false, length = 4)
    private String stdTmSrcFlg;

    @Column(name = "OWNER_FLG", nullable = false, length = 4)
    private String ownerFlg;

    @Column(name = "F1_TRANSLATION_CONTEXT", nullable = false, length = 4000)
    private String f1TranslationContext;

    @Column(name = "F1_EXTRACT_TRANSLATION_FLG", nullable = false, length = 4)
    private String f1ExtractTranslationFlg;

    public TableFieldId getId() {
        return id;
    }

    public void setId(TableFieldId id) {
        this.id = id;
    }

    public Integer getSeqNum() {
        return seqNum;
    }

    public void setSeqNum(Integer seqNum) {
        this.seqNum = seqNum;
    }

    public Boolean getRequiredSw() {
        return requiredSw;
    }

    public void setRequiredSw(Boolean requiredSw) {
        this.requiredSw = requiredSw;
    }

    public Boolean getNullableSw() {
        return nullableSw;
    }

    public void setNullableSw(Boolean nullableSw) {
        this.nullableSw = nullableSw;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getValidateSw() {
        return validateSw;
    }

    public void setValidateSw(Boolean validateSw) {
        this.validateSw = validateSw;
    }

    public Boolean getAuditInsertSw() {
        return auditInsertSw;
    }

    public void setAuditInsertSw(Boolean auditInsertSw) {
        this.auditInsertSw = auditInsertSw;
    }

    public Boolean getAuditUpdateSw() {
        return auditUpdateSw;
    }

    public void setAuditUpdateSw(Boolean auditUpdateSw) {
        this.auditUpdateSw = auditUpdateSw;
    }

    public Boolean getAuditDeleteSw() {
        return auditDeleteSw;
    }

    public void setAuditDeleteSw(Boolean auditDeleteSw) {
        this.auditDeleteSw = auditDeleteSw;
    }

    public Boolean getAllowCustomSw() {
        return allowCustomSw;
    }

    public void setAllowCustomSw(Boolean allowCustomSw) {
        this.allowCustomSw = allowCustomSw;
    }

    public String getStdTmSrcFlg() {
        return stdTmSrcFlg;
    }

    public void setStdTmSrcFlg(String stdTmSrcFlg) {
        this.stdTmSrcFlg = stdTmSrcFlg;
    }

    public String getOwnerFlg() {
        return ownerFlg;
    }

    public void setOwnerFlg(String ownerFlg) {
        this.ownerFlg = ownerFlg;
    }

    public String getF1TranslationContext() {
        return f1TranslationContext;
    }

    public void setF1TranslationContext(String f1TranslationContext) {
        this.f1TranslationContext = f1TranslationContext;
    }

    public String getF1ExtractTranslationFlg() {
        return f1ExtractTranslationFlg;
    }

    public void setF1ExtractTranslationFlg(String f1ExtractTranslationFlg) {
        this.f1ExtractTranslationFlg = f1ExtractTranslationFlg;
    }

}