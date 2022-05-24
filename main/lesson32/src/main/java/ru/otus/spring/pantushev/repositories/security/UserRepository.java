package ru.otus.spring.pantushev.repositories.security;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.pantushev.entities.security.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String userName);
}
