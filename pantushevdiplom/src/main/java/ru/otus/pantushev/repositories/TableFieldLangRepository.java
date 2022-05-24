package ru.otus.pantushev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.pantushev.entities.tableField.TableFieldLang;
import ru.otus.pantushev.entities.tableField.TableFieldLangId;

import java.util.List;
import java.util.Optional;

public interface TableFieldLangRepository extends JpaRepository<TableFieldLang, TableFieldLangId> {
    List<TableFieldLang> findById_TblNameAndId_FldName(String tblName, String fldName);
    Optional<TableFieldLang> findById(TableFieldLangId fieldLangId);
}