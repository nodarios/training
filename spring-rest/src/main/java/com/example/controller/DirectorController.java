package com.example.controller;

import com.example.assembler.DirectorAssembler;
import com.example.dto.director.DirectorDto;
import com.example.dto.director.DirectorToAddDto;
import com.example.entity.Director;
import com.example.mapper.DirectorMapper;
import com.example.service.DirectorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/directors")
public class DirectorController {

    private final DirectorService directorService;
    private final DirectorMapper directorMapper;
    private final DirectorAssembler directorAssembler;

    @GetMapping("/{id}")
    @Operation(summary = "get directorDto by id")
    public EntityModel<DirectorDto> get(@PathVariable Long id) {
        Director director = directorService.get(id);
        DirectorDto directorDto = directorMapper.mapEntityToDto(director);
        return directorAssembler.toModel(directorDto);
    }

    @GetMapping
    @Operation(summary = "get all directors")
    public CollectionModel<EntityModel<DirectorDto>> getAll() {
        List<Director> directors = directorService.getAll();
        List<DirectorDto> directorDtos = directorMapper.mapEntitiesToDtos(directors);
        return directorAssembler.toCollectionModel(directorDtos);
    }

    @PostMapping
    @Operation(summary = "add new directorDto")
    public EntityModel<DirectorDto> add(@RequestBody DirectorToAddDto directorToAddDto) {
        Director director = directorService.add(directorMapper.mapDtoToEntity(directorToAddDto));
        return directorAssembler.toModel(directorMapper.mapEntityToDto(director));
    }

    @PutMapping
    @Operation(summary = "update existing directorDto")
    public EntityModel<DirectorDto> update(@RequestBody DirectorDto directorDto) {
        Director director = directorService.update(directorMapper.mapDtoToEntity(directorDto));
        return directorAssembler.toModel(directorMapper.mapEntityToDto(director));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete directorDto")
    public void delete(@PathVariable Long id) {
        directorService.delete(id);
    }

}
