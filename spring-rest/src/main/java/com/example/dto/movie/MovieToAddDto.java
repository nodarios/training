package com.example.dto.movie;

import com.example.dto.director.DirectorDto;

public record MovieToAddDto(String title, DirectorDto directorDto) {
}
