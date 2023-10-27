package com.example.dto.movie;

import com.example.dto.director.DirectorDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MovieDto {

    private Long id;

    private String title;

    private DirectorDto directorDto;

}
