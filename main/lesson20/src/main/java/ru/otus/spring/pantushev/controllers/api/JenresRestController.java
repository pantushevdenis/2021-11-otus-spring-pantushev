package ru.otus.spring.pantushev.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.otus.spring.pantushev.domain.Jenre;
import ru.otus.spring.pantushev.repositories.JenresRepository;


@RequestMapping("/api")
@RestController
public class JenresRestController {
    private final JenresRepository jenresRepository;


    @Autowired
    public JenresRestController(JenresRepository jenresRepository) {
        this.jenresRepository = jenresRepository;
    }


    @GetMapping("/jenres")
    public Flux<Jenre> getJenres() {
        Flux<Jenre> jenres = jenresRepository.findAll();
        return jenres;
    }
}
