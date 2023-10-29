package com.example.dto.movie;

import com.example.dto.director.DirectorDto;

public record MovieDto(Long id, String title, DirectorDto directorDto) {
}
