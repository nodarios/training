package com.example.service;

import com.example.entity.Movie;
import com.example.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public Movie get(Long id) {
        return movieRepository
                .findById(id)
                .orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    public Movie add(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie update(Movie movie) {
        return movieRepository.save(movie);
    }

    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

}
