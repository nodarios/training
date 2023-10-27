package com.example.controller;

import com.example.dto.movie.MovieToAddDto;
import com.example.dto.movie.MovieDto;
import com.example.dto.movie.MovieToUpdateDto;
import com.example.entity.Movie;
import com.example.mapper.MovieMapper;
import com.example.service.MovieService;
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
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @GetMapping("/{id}")
    @Operation(summary = "get movie by id")
    public MovieDto get(@PathVariable Long id) {
        Movie movie = movieService.get(id);
        return movieMapper.mapEntityToDto(movie);
    }

    @GetMapping
    @Operation(summary = "get all movies")
    public List<MovieDto> getAll() {
        List<Movie> movies = movieService.getAll();
        return movieMapper.mapEntitiesToDtos(movies);
    }

    @PostMapping
    @Operation(summary = "add new movie")
    public MovieDto add(@RequestBody MovieToAddDto movieToAddDto) {
        Movie movie = movieService.add(movieMapper.mapDtoToEntity(movieToAddDto));
        return movieMapper.mapEntityToDto(movie);
    }

    @PutMapping
    @Operation(summary = "update existing movie")
    public MovieDto update(@RequestBody MovieToUpdateDto movieToUpdateDto) {
        Movie movie = movieService.update(movieMapper.mapDtoToEntity(movieToUpdateDto));
        return movieMapper.mapEntityToDto(movie);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete movie")
    public void delete(@PathVariable Long id) {
        movieService.delete(id);
    }

}
