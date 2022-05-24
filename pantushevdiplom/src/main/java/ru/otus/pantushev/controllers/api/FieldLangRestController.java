package ru.otus.pantushev.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.pantushev.dto.field.FieldLangDto;
import ru.otus.pantushev.dto.field.FieldLangMapper;
import ru.otus.pantushev.entities.field.FieldLandId;
import ru.otus.pantushev.entities.field.FieldLang;
import ru.otus.pantushev.repositories.FieldLangRepository;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class FieldLangRestController {
    private FieldLangRepository fieldLangRepository;

    @Autowired
    public FieldLangRestController(FieldLangRepository tableLangRepository) {
        this.fieldLangRepository = tableLangRepository;
    }

    @GetMapping("/field/{name}/language")
    public List<FieldLangDto> getLanguages(@PathVariable("name") String name) {
        name = String.format("%-30.30s", name);
        List<FieldLang> languages = fieldLangRepository.findAllById_FldName(name);
        return languages.stream()
            .map(FieldLangMapper::toDto)
            .collect(Collectors.toList());
    }

    @GetMapping("/field/{name}/language/{language}")
    public FieldLangDto getLanguage(@PathVariable("name") String fldName, @PathVariable("language") String language) {
        fldName = String.format("%-30.30s", fldName);
        language = String.format("%-3.3s", language);
        return fieldLangRepository.findById(new FieldLandId(fldName, language))
            .map(FieldLangMapper::toDto)
            .orElseThrow(NotFoundException::new);
    }

}
