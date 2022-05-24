package ru.otus.pantushev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.pantushev.entities.field.FieldLandId;
import ru.otus.pantushev.entities.field.FieldLang;

import java.util.List;
import java.util.Optional;

public interface FieldLangRepository extends JpaRepository<FieldLang, FieldLandId> {
    List<FieldLang> findAllById_FldName(String s);
    Optional<FieldLang> findById(FieldLandId fieldLangId);
}