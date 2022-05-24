package ru.otus.pantushev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.pantushev.entities.constraint.ConstraintField;
import ru.otus.pantushev.entities.constraint.ConstraintFieldId;

public interface ConstraintFieldRepository extends JpaRepository<ConstraintField, ConstraintFieldId> {
}