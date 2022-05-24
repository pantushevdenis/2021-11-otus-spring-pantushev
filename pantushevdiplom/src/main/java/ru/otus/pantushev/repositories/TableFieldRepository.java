package ru.otus.pantushev.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.pantushev.entities.tableField.TableField;
import ru.otus.pantushev.entities.tableField.TableFieldId;

import java.util.List;
import java.util.Optional;

public interface TableFieldRepository extends JpaRepository<TableField, TableFieldId> {
    Page<TableField> findById_TblName(String tblName, Pageable pageable);

    Page<TableField> findById_TblNameAndId_FldNameContains(@Param("tblName") String tblName, @Param("fldName") String fldName, Pageable pageable);

    Optional<TableField> findById(TableFieldId id);

}