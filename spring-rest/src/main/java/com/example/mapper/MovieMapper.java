package com.example.mapper;

import com.example.dto.movie.MovieDto;
import com.example.dto.movie.MovieToAddDto;
import com.example.dto.movie.MovieToUpdateDto;
import com.example.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovieMapper {

    @Mapping(source = "directorDto", target = "director")
    Movie mapDtoToEntity(MovieToAddDto movieToAddDto);

    @Mapping(source = "directorDto", target = "director")
    Movie mapDtoToEntity(MovieToUpdateDto movieToUpdateDto);

    @Mapping(source = "director", target = "directorDto")

    MovieDto mapEntityToDto(Movie movie);

    @Mapping(source = "director", target = "directorDto")
    List<MovieDto> mapEntitiesToDtos(List<Movie> movies);

}
