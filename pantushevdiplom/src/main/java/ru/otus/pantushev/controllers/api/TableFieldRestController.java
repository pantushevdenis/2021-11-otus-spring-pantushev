package ru.otus.pantushev.controllers.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import ru.otus.pantushev.dto.tableField.TableFieldDto;
import ru.otus.pantushev.dto.tableField.TableFieldMapper;
import ru.otus.pantushev.entities.tableField.TableField;
import ru.otus.pantushev.entities.tableField.TableFieldId;
import ru.otus.pantushev.repositories.TableFieldRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping("/api")
@RestController
public class TableFieldRestController {
    private TableFieldRepository tableFieldRepository;

    @Autowired
    public TableFieldRestController(TableFieldRepository tableFieldRepository) {
        this.tableFieldRepository = tableFieldRepository;
    }


    @GetMapping("/table/{table_name}/field")
    public Page<TableFieldDto> getTableFields(
        @PathVariable("table_name") String tableName,
        @RequestParam(value="name_filter", required = false) String nameFilter,
        @RequestParam(value="page", required = false, defaultValue = "0") Integer page,
        @RequestParam(value="size", required = false, defaultValue = "100") Integer size
    ) {
        log.info("begin getTableFields");

        tableName = String.format("%-30.30s", tableName);
        Page<TableField> tableFields;
        if (!StringUtils.isBlank(nameFilter)) {
            tableFields = tableFieldRepository.findById_TblNameAndId_FldNameContains(tableName, nameFilter, PageRequest.of(page, size));
        }
        else {
            tableFields = tableFieldRepository.findById_TblName(tableName, PageRequest.of(page, size));
        }
        Page<TableFieldDto> res = tableFields.map(TableFieldMapper::toDto);

        log.info("end getTableFields");

        return res;
    }


    @GetMapping("/table/{table_name}/field/{field_name}")
    public TableFieldDto getTableFieldsByName(
        @PathVariable("table_name") String tableName,
        @PathVariable("field_name") String fieldName
    ) {
        tableName = String.format("%-30.30s", tableName);
        fieldName = String.format("%-30.30s", fieldName);
        return tableFieldRepository.findById(new TableFieldId(tableName,fieldName))
            .map(TableFieldMapper::toDto)
            .orElseThrow(NotFoundException::new);
    }


}
