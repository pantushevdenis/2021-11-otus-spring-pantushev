package ru.otus.pantushev.entities.table;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Data
@Entity
@Table(name = "CI_MD_TBL_L")
public class TableLang {
    @EmbeddedId
    private TableLangId id;

    @Column(name = "VERSION", nullable = false)
    private Integer version;

    @Column(name = "DESCR", nullable = false, length = 60)
    private String descr;

    @Column(name = "DESCRLONG", nullable = false, length = 4000)
    private String descrlong;

    @Column(name = "OWNER_FLG", nullable = false, length = 4)
    private String ownerFlg;

    public TableLang(TableLangId id) {
        this.id = id;
    }
}