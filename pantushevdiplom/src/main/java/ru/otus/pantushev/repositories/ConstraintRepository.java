package ru.otus.pantushev.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.pantushev.entities.constraint.Constraint;
import ru.otus.pantushev.entities.constraint.ConstraintId;

public interface ConstraintRepository extends JpaRepository<Constraint, ConstraintId> {
}