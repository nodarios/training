package com.example.dto.movie;

import com.example.dto.director.DirectorDto;

public record MovieToUpdateDto(Long id, String title, DirectorDto directorDto) {
}
