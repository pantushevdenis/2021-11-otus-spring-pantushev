package ru.otus.pantushev.controllers.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.otus.pantushev.dto.table.TableDto;
import ru.otus.pantushev.dto.table.TableLangDto;
import ru.otus.pantushev.dto.table.TableLangMapper;
import ru.otus.pantushev.dto.table.TableMapper;
import ru.otus.pantushev.entities.table.Table;
import ru.otus.pantushev.entities.table.TableLang;
import ru.otus.pantushev.entities.table.TableLangId;
import ru.otus.pantushev.exceptions.TableDataWrongException;
import ru.otus.pantushev.exceptions.TableNotFoundException;
import ru.otus.pantushev.repositories.TableLangRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping("/api")
@RestController
public class TableLangRestController {
    private TableLangRepository tableLangRepository;

    @Autowired
    public TableLangRestController(TableLangRepository tableLangRepository) {
        this.tableLangRepository = tableLangRepository;
    }

    @GetMapping("/table/{name}/language")
    public List<TableLangDto> getLanguages(@PathVariable("name") String name) {
        log.info("begin getLanguages");

        name = String.format("%-30.30s", name);
        List<TableLang> l = tableLangRepository.findAllById_TblName(name);
        List<TableLangDto> res = l.stream()
            .map(TableLangMapper::toDto)
            .collect(Collectors.toList());

        log.info("end getLanguages");
        return res;
    }

    @GetMapping("/table/{name}/language/{language}")
    public TableLangDto getLanguage(@PathVariable("name") String name, @PathVariable("language") String language) {
        log.info("begin getLanguage");

        name = String.format("%-30.30s", name);
        language = String.format("%-3.3s", language);
        TableLangDto res = tableLangRepository.findById(new TableLangId(name, language))
            .map(TableLangMapper::toDto)
            .orElseThrow(NotFoundException::new);

        log.info("end getLanguage");

        return res;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/table/{name}/language/{language}")
    public TableLangDto postSave(@RequestBody TableLangDto dto) {
        if (StringUtils.isBlank(dto.getTableName())) {
            throw new TableDataWrongException("Идентификатор таблицы не указан!");
        }
        if (StringUtils.isBlank(dto.getLanguageCd())) {
            throw new TableDataWrongException("Идентификатор языка не указан!");
        }
        validateData(dto);

        TableLang tableLang = TableLangMapper.toEntity(dto);
        TableLangDto tableLangDto = TableLangMapper.toDto(tableLangRepository.save(tableLang));
        return tableLangDto;
    }

    @PutMapping("/table/{name}/language/{language}")
    public TableLangDto putSave(@RequestBody TableLangDto dto) {
        if (StringUtils.isBlank(dto.getTableName())) {
            throw new TableDataWrongException("Идентификатор таблицы не указан!");
        }
        if (StringUtils.isBlank(dto.getLanguageCd())) {
            throw new TableDataWrongException("Идентификатор языка не указан!");
        }
        validateData(dto);

        String id = String.format("%-30.30s", dto.getTableName());
        TableLang tableLang = tableLangRepository.findById(
            new TableLangId(dto.getTableName(), dto.getLanguageCd())
        ).orElseThrow(() -> {
            return new TableNotFoundException(dto.getTableName());
        });
        TableLangMapper.toEntity(dto, tableLang);
        TableLang tableLangUpdated = tableLangRepository.save(tableLang);
        TableLangDto tableLangDto = TableLangMapper.toDto(tableLangUpdated);
        return tableLangDto;
    }

    private void validateData(TableLangDto dto) {
        if (StringUtils.isBlank(dto.getDescr())) {
            throw new TableDataWrongException("Наименование не указано!");
        }
    }



}
