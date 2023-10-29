package com.example.controller;

import com.example.assembler.MovieAssembler;
import com.example.dto.movie.MovieDto;
import com.example.dto.movie.MovieToAddDto;
import com.example.entity.Movie;
import com.example.mapper.MovieMapper;
import com.example.service.MovieService;
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
@RequestMapping
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;
    private final MovieAssembler movieAssembler;

    @GetMapping("/movies/{id}")
    @Operation(summary = "get movie by id")
    public EntityModel<MovieDto> get(@PathVariable Long id) {
        Movie movie = movieService.get(id);
        MovieDto movieDto = movieMapper.mapEntityToDto(movie);
        return movieAssembler.toModel(movieDto);
    }

    @GetMapping("/movies")
    @Operation(summary = "get all movies")
    public CollectionModel<EntityModel<MovieDto>> getAll() {
        List<Movie> movies = movieService.getAll();
        List<MovieDto> movieDtos = movieMapper.mapEntitiesToDtos(movies);
        return movieAssembler.toCollectionModel(movieDtos);
    }

    @GetMapping("/director/{id}/movies")
    @Operation(summary = "get all movies by director")
    public CollectionModel<EntityModel<MovieDto>> getAllByDirectorId(@PathVariable Long id) {
        List<Movie> movies = movieService.getAllByDirectorId(id);
        List<MovieDto> movieDtos = movieMapper.mapEntitiesToDtos(movies);
        return movieAssembler.toCollectionModel(movieDtos);
    }

    @PostMapping("/movies")
    @Operation(summary = "add new movie")
    public EntityModel<MovieDto> add(@RequestBody MovieToAddDto movieToAddDto) {
        Movie movie = movieService.add(movieMapper.mapDtoToEntity(movieToAddDto));
        return movieAssembler.toModel(movieMapper.mapEntityToDto(movie));
    }

    @PutMapping("/movies")
    @Operation(summary = "update existing movie")
    public EntityModel<MovieDto> update(@RequestBody MovieDto movieDto) {
        Movie movie = movieService.update(movieMapper.mapDtoToEntity(movieDto));
        return movieAssembler.toModel(movieMapper.mapEntityToDto(movie));
    }

    @DeleteMapping("/movies/{id}")
    @Operation(summary = "delete movie")
    public void delete(@PathVariable Long id) {
        movieService.delete(id);
    }

}
