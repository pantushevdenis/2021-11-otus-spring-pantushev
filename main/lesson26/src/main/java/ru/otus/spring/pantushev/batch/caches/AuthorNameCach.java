package ru.otus.spring.pantushev.batch.caches;

import org.springframework.stereotype.Component;
import ru.otus.spring.pantushev.repositories.AuthorsRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class AuthorNameCach {
    private final AuthorsRepository authorsRepository;
    private final Set<String> set = new HashSet<>();

    public AuthorNameCach(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    public boolean exists(String name) {
        if(set.contains(name)) {
            return true;
        }
        else if (authorsRepository.existsAuthorByFullName(name)) {
            set.add(name);
            return true;
        }
        else {
            return false;
        }
    }
}
