package ru.otus.pantushev.controllers.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.otus.pantushev.dto.table.TableDto;
import ru.otus.pantushev.dto.table.TableMapper;
import ru.otus.pantushev.entities.table.Table;
import ru.otus.pantushev.exceptions.TableDataWrongException;
import ru.otus.pantushev.exceptions.TableNotFoundException;
import ru.otus.pantushev.repositories.TableRepository;

@Slf4j
@RequestMapping("/api")
@RestController
public class TableRestController {
    private TableRepository tableRepository;

    @Autowired
    public TableRestController(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @GetMapping("/table")
    public Page<TableDto> getTables(
        @RequestParam(value="name_filter", required = false) String nameFilter,
        @RequestParam(value="page", required = false, defaultValue = "0") Integer page,
        @RequestParam(value="size", required = false, defaultValue = "30") Integer size,
        @RequestParam(value="prev", required=false, defaultValue="false") boolean prev,
        @RequestParam(value="next", required=false, defaultValue="false") boolean next
    ) {
        log.info("begin getTables");

        Page<Table> tables;
        if (prev) {
            page--;
        }
        else if(next) {
            page++;
        }
        if (!StringUtils.isBlank(nameFilter)) {
            tables = tableRepository.findByIdContains(nameFilter, PageRequest.of(page, size));
        }
        else {
            tables = tableRepository.findAll(PageRequest.of(page, size));
        }
        Page<TableDto> tableDtos = tables.map(TableMapper::toDto);

        log.info("end getTables");

        return tableDtos;
    }


    @GetMapping("/table/{name}")
    public TableDto getTableByName(@PathVariable("name") String name) {
        name = String.format("%-30.30s", name);
        return tableRepository.findById(name)
            .map(TableMapper::toDto)
            .orElseThrow(NotFoundException::new);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/table")
    public TableDto postSave(@RequestBody TableDto dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new TableDataWrongException("Идентификатор таблицы не указан!");
        }
        validateData(dto);

        Table table = TableMapper.toEntity(dto);
        TableDto tableDto = TableMapper.toDto(tableRepository.save(table));
        return tableDto;
    }

    @PutMapping("/table")
    public TableDto putSave(@RequestBody TableDto dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new TableDataWrongException("Идентификатор таблицы не указан!");
        }
        validateData(dto);

        String id = String.format("%-30.30s", dto.getId());
        Table table = tableRepository.findById(id).orElseThrow(() -> {
            return new TableNotFoundException(dto.getId());
        });
        TableMapper.toEntity(dto, table);
        Table tableUpdated = tableRepository.save(table);
        TableDto tableDto = TableMapper.toDto(tableUpdated);
        return tableDto;
    }


    private void validateData(TableDto dto) {
    }



}
