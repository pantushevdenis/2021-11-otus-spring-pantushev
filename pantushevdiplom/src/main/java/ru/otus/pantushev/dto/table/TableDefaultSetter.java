package ru.otus.pantushev.dto.table;

import ru.otus.pantushev.entities.table.Table;

public class TableDefaultSetter {
    public static void setDfaultValues(Table table) {
        table.setTblTypeFlg("TBL");
        table.setMaintProgName(" ");
        table.setVersion(1);
        table.setDataGrpId(" ");
        table.setLangTblName(" ");
        table.setAuditTblName(" ");
        table.setUpgradeActFlg(" ");
        table.setEnableRiSw(false);
        table.setDttmDataTyFlg("LG");
        table.setSystemTblSw(false);
        table.setEnableDdSw(true);
        table.setDataCnvRoleFlg("NCNV");
        table.setAuditPgmName(" ");
        table.setKeyPrefixLengh(0);
        table.setKeyTblName(" ");
        table.setHelpTextUrl(" ");
        table.setOwnerFlg("CM");
        table.setAuditPgmTypeFlg(" ");
        table.setCacheFlg("NONE");
        table.setKeyValidationFlg("ALL");
        table.setF1TranslationContext(" ");
        table.setF1ExtractTranslationFlg("F1NO");
        table.setTblClassificationFlg("F1UC");
        table.setTblVolumeFlg("F1UC");
        table.setCharEntityFlg(" ");
    }
}
