package ru.otus.spring.pantushev.repositories.security;

import org.springframework.data.repository.CrudRepository;
import ru.otus.spring.pantushev.domain.security.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String userName);
}
