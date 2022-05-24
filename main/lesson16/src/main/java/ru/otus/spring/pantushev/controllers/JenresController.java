package ru.otus.spring.pantushev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring.pantushev.domain.Jenre;
import ru.otus.spring.pantushev.repositories.JenresRepository;

import java.util.List;

@Controller
public class JenresController {
    private final JenresRepository jenresRepository;

    @Autowired
    public JenresController(JenresRepository jenresRepository) {
        this.jenresRepository = jenresRepository;
    }

    @GetMapping("/jenres")
    public String listPage(Model model) {
        List<Jenre> jenres = jenresRepository.findAll();
        model.addAttribute("jenres", jenres);
        return "jenres";
    }
}
