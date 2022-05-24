package ru.otus.spring.pantushev.batch.caches;

import org.springframework.stereotype.Component;
import ru.otus.spring.pantushev.repositories.JenresRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class JenreNameCach {

    private final JenresRepository jenresRepository;
    private final Set<String> set = new HashSet<>();

    public JenreNameCach(JenresRepository jenresRepository) {
        this.jenresRepository = jenresRepository;
    }

    public boolean exists(String name) {
        if(set.contains(name)) {
            return true;
        }
        else if (jenresRepository.existsJenreByFullName(name)) {
            set.add(name);
            return true;
        }
        else {
            return false;
        }
    }
}
