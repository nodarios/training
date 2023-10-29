package com.example.assembler;

import com.example.controller.DirectorController;
import com.example.controller.MovieController;
import com.example.dto.director.DirectorDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DirectorAssembler implements SimpleRepresentationModelAssembler<DirectorDto> {

    @Override
    public void addLinks(EntityModel<DirectorDto> entityModel) {
        Long directorId = Objects.requireNonNull(entityModel.getContent()).id();
        Link directorLink = linkTo(methodOn(DirectorController.class).get(directorId)).withSelfRel();
        Link directorMoviesLink = linkTo(methodOn(MovieController.class).getAllByDirectorId(directorId)).withRel("directorMovies");
        entityModel.add(directorLink, directorMoviesLink);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<DirectorDto>> collectionModel) {
        Link directorsLink = linkTo(methodOn(DirectorController.class).getAll()).withSelfRel();
        collectionModel.add(directorsLink);

    }

}
