package ru.otus.pantushev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.pantushev.entities.table.TableLang;
import ru.otus.pantushev.entities.table.TableLangId;

import java.util.List;
import java.util.Optional;

public interface TableLangRepository extends JpaRepository<TableLang, TableLangId> {
    List<TableLang> findAllById_TblName(String s);
    Optional<TableLang> findById(TableLangId tableLangId);
}