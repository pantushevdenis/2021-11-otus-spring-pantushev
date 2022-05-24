package ru.otus.spring.pantushev.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.pantushev.domain.Author;
import ru.otus.spring.pantushev.domain.Jenre;
import ru.otus.spring.pantushev.dto.AuthorDto;
import ru.otus.spring.pantushev.repositories.JenresRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController
public class JenresRestController {
    private JenresRepository jenresRepository;

    @Autowired
    public JenresRestController(JenresRepository jenresRepository) {
        this.jenresRepository = jenresRepository;
    }

    @GetMapping("/jenres")
    public List<Jenre> getJenres() {
        List<Jenre> jenres = jenresRepository.findAll();
        return jenres;
    }

    @GetMapping("/dropdowns/jenres")
    public List<Jenre> getDropdownJenres() {
        List<Jenre> jenres = jenresRepository.findAll();
        return jenres;
    }


}
