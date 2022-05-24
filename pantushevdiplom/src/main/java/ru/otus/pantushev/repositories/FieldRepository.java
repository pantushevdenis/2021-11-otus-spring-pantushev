package ru.otus.pantushev.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.pantushev.entities.field.Field;

import java.util.Optional;

public interface FieldRepository extends JpaRepository<Field, String> {
    Page<Field> findByIdContains(@Param("id") String id, Pageable pageable);
    Optional<Field> findById(String fldName);

}