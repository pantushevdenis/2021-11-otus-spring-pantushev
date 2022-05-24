package ru.otus.pantushev.entities.table;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@Entity
@javax.persistence.Table(name = "CI_MD_TBL")
public class Table {
    @Id
    @Column(name = "TBL_NAME", nullable = false, length = 30)
    private String id;

    @Column(name = "TBL_TYPE_FLG", nullable = false, length = 4)
    private String tblTypeFlg;

    @Column(name = "MAINT_PROG_NAME", nullable = false, length = 8)
    private String maintProgName;

    @Column(name = "VERSION", nullable = false)
    private Integer version;

    @Column(name = "DATA_GRP_ID", nullable = false, length = 5)
    private String dataGrpId;

    @Column(name = "LANG_TBL_NAME", nullable = false, length = 30)
    private String langTblName;

    @Column(name = "AUDIT_TBL_NAME", nullable = false, length = 30)
    private String auditTblName;

    @Column(name = "UPGRADE_ACT_FLG", nullable = false, length = 2)
    private String upgradeActFlg;

    @Column(name = "ENABLE_RI_SW", nullable = false)
    private Boolean enableRiSw = false;

    @Column(name = "DTTM_DATA_TY_FLG", nullable = false, length = 4)
    private String dttmDataTyFlg;

    @Column(name = "SYSTEM_TBL_SW", nullable = false)
    private Boolean systemTblSw = false;

    @Column(name = "ENABLE_DD_SW", nullable = false)
    private Boolean enableDdSw = false;

    @Column(name = "DATA_CNV_ROLE_FLG", nullable = false, length = 4)
    private String dataCnvRoleFlg;

    @Column(name = "AUDIT_PGM_NAME", nullable = false, length = 254)
    private String auditPgmName;

    @Column(name = "KEY_TYPE_FLG", nullable = false, length = 4)
    private String keyTypeFlg;

    @Column(name = "KEY_PREFIX_LENGH", nullable = false)
    private Integer keyPrefixLengh;

    @Column(name = "KEY_TBL_NAME", nullable = false, length = 30)
    private String keyTblName;

    @Column(name = "HELP_TEXT_URL", nullable = false, length = 254)
    private String helpTextUrl;

    @Column(name = "OWNER_FLG", nullable = false, length = 4)
    private String ownerFlg;

    @Column(name = "OBJ_ENTITY_NAME", nullable = false, length = 60)
    private String objEntityName;

    @Column(name = "AUDIT_PGM_TYPE_FLG", nullable = false, length = 4)
    private String auditPgmTypeFlg;

    @Column(name = "CACHE_FLG", nullable = false, length = 4)
    private String cacheFlg;

    @Column(name = "KEY_VALIDATION_FLG", nullable = false, length = 4)
    private String keyValidationFlg;

    @Column(name = "F1_TRANSLATION_CONTEXT", nullable = false, length = 4000)
    private String f1TranslationContext;

    @Column(name = "F1_EXTRACT_TRANSLATION_FLG", nullable = false, length = 4)
    private String f1ExtractTranslationFlg;

    @Column(name = "TBL_CLASSIFICATION_FLG", nullable = false, length = 4)
    private String tblClassificationFlg;

    @Column(name = "TBL_VOLUME_FLG", nullable = false, length = 4)
    private String tblVolumeFlg;

    @Column(name = "CHAR_ENTITY_FLG", nullable = false, length = 4)
    private String charEntityFlg;

    public Table(String id) {
        this.id = id;
    }
}