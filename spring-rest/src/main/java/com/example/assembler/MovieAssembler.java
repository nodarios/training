package com.example.assembler;

import com.example.controller.MovieController;
import com.example.dto.movie.MovieDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MovieAssembler implements SimpleRepresentationModelAssembler<MovieDto> {

    @Override
    public void addLinks(EntityModel<MovieDto> entityModel) {
        Long movieId = Objects.requireNonNull(entityModel.getContent()).id();
        Link movieLink = linkTo(methodOn(MovieController.class).get(movieId)).withSelfRel();
        entityModel.add(movieLink);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<MovieDto>> collectionModel) {
        Link moviesLink = linkTo(methodOn(MovieController.class).getAll()).withSelfRel();
        collectionModel.add(moviesLink);

    }

}
