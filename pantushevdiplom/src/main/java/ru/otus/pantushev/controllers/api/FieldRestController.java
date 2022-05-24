package ru.otus.pantushev.controllers.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.otus.pantushev.dto.field.FieldDto;
import ru.otus.pantushev.dto.field.FieldMapper;
import ru.otus.pantushev.dto.table.TableDto;
import ru.otus.pantushev.dto.table.TableMapper;
import ru.otus.pantushev.entities.field.Field;
import ru.otus.pantushev.entities.table.Table;
import ru.otus.pantushev.exceptions.FieldDataWrongException;
import ru.otus.pantushev.exceptions.FieldNotFoundException;
import ru.otus.pantushev.exceptions.TableDataWrongException;
import ru.otus.pantushev.exceptions.TableNotFoundException;
import ru.otus.pantushev.repositories.FieldRepository;

import java.util.Optional;

@Slf4j
@RequestMapping("/api")
@RestController
public class FieldRestController {
    private FieldRepository fieldRepository;

    @Autowired
    public FieldRestController(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    @GetMapping("/field")
    public Page<FieldDto> getFields(
        @RequestParam(value="name_filter", required = false) String nameFilter,
        @RequestParam(value="page", required = false, defaultValue = "0") Integer page,
        @RequestParam(value="size", required = false, defaultValue = "30") Integer size,
        @RequestParam(value="prev", required=false, defaultValue="false") boolean prev,
        @RequestParam(value="next", required=false, defaultValue="false") boolean next
    ) {
        log.info("begin getFields");

        Page<Field> fields;
        if (prev) {
            page--;
        }
        else if(next) {
            page++;
        }
        if (!StringUtils.isBlank(nameFilter)) {
            fields = fieldRepository.findByIdContains(nameFilter, PageRequest.of(page, size));
        }
        else {
            fields = fieldRepository.findAll(PageRequest.of(page, size));
        }

        Page<FieldDto> res = fields.map(FieldMapper::toDto);

        log.info("end getFields");

        return res;
    }

    @GetMapping("/field/{field_name}")
    public FieldDto getField(@PathVariable("field_name") String fldName) {
        log.info("begin getField");

        fldName = String.format("%-30.30s", fldName);
        Optional<Field> f = fieldRepository.findById(fldName);
        FieldDto res = f
            .map(FieldMapper::toDto)
            .orElseThrow(NotFoundException::new);

        log.info("end getField");

        return res;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/field")
    public FieldDto postSave(@RequestBody FieldDto dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new FieldDataWrongException("Идентификатор поля не указан!");
        }
        validateData(dto);

        Field field = FieldMapper.toEntity(dto);
        FieldDto fieldDto = FieldMapper.toDto(fieldRepository.save(field));
        return fieldDto;
    }

    @PutMapping("/field")
    public FieldDto putSave(@RequestBody FieldDto dto) {
        if (StringUtils.isBlank(dto.getId())) {
            throw new TableDataWrongException("Идентификатор таблицы не указан!");
        }
        validateData(dto);

        String id = String.format("%-30.30s", dto.getId());
        Field field = fieldRepository.findById(id).orElseThrow(() -> {
            return new FieldNotFoundException(dto.getId());
        });
        FieldMapper.toEntity(dto, field);
        Field fieldUpdated = fieldRepository.save(field);
        FieldDto fieldDto = FieldMapper.toDto(fieldUpdated);
        return fieldDto;
    }

    private void validateData(FieldDto dto) {
        if (StringUtils.isBlank(dto.getObjPropertyName())) {
            throw new FieldDataWrongException("Поле Класс объекта не указан!");
        }
        if (dto.getSignedSw() ) {
            throw new FieldDataWrongException("Поле Значность не указан!");
        }
        if (dto.getFldPrecision() <= 0) {
            throw new FieldDataWrongException("Поле Количество знаков не указан (либо отрицательно)!");
        }
    }


}
