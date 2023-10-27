package com.example.controller;

import com.example.dto.director.DirectorDto;
import com.example.dto.director.DirectorToAddDto;
import com.example.dto.director.DirectorToUpdateDto;
import com.example.entity.Director;
import com.example.mapper.DirectorMapper;
import com.example.service.DirectorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/{id}")
    @Operation(summary = "get directorDto by id")
    public DirectorDto get(@PathVariable Long id) {
        Director director = directorService.get(id);
        return directorMapper.mapEntityToDto(director);
    }

    @GetMapping
    @Operation(summary = "get all directors")
    public List<DirectorDto> getAll() {
        List<Director> directors = directorService.getAll();
        return directorMapper.mapEntitiesToDtos(directors);
    }

    @PostMapping
    @Operation(summary = "add new directorDto")
    public DirectorDto add(@RequestBody DirectorToAddDto directorToAddDto) {
        Director director = directorService.add(directorMapper.mapDtoToEntity(directorToAddDto));
        return directorMapper.mapEntityToDto(director);
    }

    @PutMapping
    @Operation(summary = "update existing directorDto")
    public DirectorDto update(@RequestBody DirectorToUpdateDto directorToUpdateDto) {
        Director director = directorService.update(directorMapper.mapDtoToEntity(directorToUpdateDto));
        return directorMapper.mapEntityToDto(director);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete directorDto")
    public void delete(@PathVariable Long id) {
        directorService.delete(id);
    }

}
