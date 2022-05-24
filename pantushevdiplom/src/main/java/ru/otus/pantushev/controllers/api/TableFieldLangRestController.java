package ru.otus.pantushev.controllers.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.pantushev.dto.tableField.TableFieldLangDto;
import ru.otus.pantushev.dto.tableField.TableFieldLangMapper;
import ru.otus.pantushev.entities.tableField.TableFieldLang;
import ru.otus.pantushev.entities.tableField.TableFieldLangId;
import ru.otus.pantushev.repositories.TableFieldLangRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api")
public class TableFieldLangRestController {
    private TableFieldLangRepository tableFieldLangRepository;

    @Autowired
    public TableFieldLangRestController(TableFieldLangRepository tableFieldLangRepository) {
        this.tableFieldLangRepository = tableFieldLangRepository;
    }

    @GetMapping("/table/{table_name}/field/{field_name}/language")
    public List<TableFieldLangDto> getLanguages(
        @PathVariable("table_name") String tblName,
        @PathVariable("field_name") String fldName
    ) {
        log.info("begin getLanguages");

        tblName = String.format("%-30.30s", tblName);
        fldName = String.format("%-30.30s", fldName);
        List<TableFieldLang> languages = tableFieldLangRepository.findById_TblNameAndId_FldName(tblName, fldName);
        List<TableFieldLangDto> res = languages.stream()
            .map(TableFieldLangMapper::toDto)
            .collect(Collectors.toList());

        log.info("end getLanguages");

        return res;
    }

    @GetMapping("/table/{table_name}/field/{field_name}/language/{language}")
    public TableFieldLangDto getLanguage(
        @PathVariable("table_name") String tblName,
        @PathVariable("field_name") String fldName,
        @PathVariable("language") String language
    ) {
        log.info("begin getLanguage");

        tblName = String.format("%-30.30s", tblName);
        fldName = String.format("%-30.30s", fldName);
        language = String.format("%-3.3s", language);
        TableFieldLangDto res = tableFieldLangRepository.findById(new TableFieldLangId(tblName, fldName, language))
            .map(TableFieldLangMapper::toDto)
            .orElseThrow(NotFoundException::new);

        log.info("end getLanguage");

        return res;
    }

}
