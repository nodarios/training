package com.example.mapper;

import com.example.dto.director.DirectorDto;
import com.example.dto.director.DirectorToAddDto;
import com.example.dto.director.DirectorToUpdateDto;
import com.example.entity.Director;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DirectorMapper {

    Director mapDtoToEntity(DirectorToAddDto directorToAddDto);

    Director mapDtoToEntity(DirectorToUpdateDto directorToUpdateDto);

    DirectorDto mapEntityToDto(Director director);
    List<DirectorDto> mapEntitiesToDtos(List<Director> directors);

}
