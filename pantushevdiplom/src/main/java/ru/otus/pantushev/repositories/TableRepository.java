package ru.otus.pantushev.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.pantushev.entities.field.Field;
import ru.otus.pantushev.entities.table.Table;

import java.util.List;
import java.util.Optional;

public interface TableRepository extends JpaRepository<Table, String> {
    Page<Table> findByIdContains(@Param("id") String id, Pageable pageable);
    Optional<Table> findById(String id);
}