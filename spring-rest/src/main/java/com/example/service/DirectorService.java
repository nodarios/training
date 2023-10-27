package com.example.service;

import com.example.entity.Director;
import com.example.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectorService {

    private final DirectorRepository directorRepository;

    @Transactional(readOnly = true)
    public Director get(Long id) {
        return directorRepository
                .findById(id)
                .orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<Director> getAll() {
        return directorRepository.findAll();
    }

    public Director add(Director director) {
        return directorRepository.save(director);
    }

    public Director update(Director director) {
        return directorRepository.save(director);
    }

    public void delete(Long id) {
        directorRepository.deleteById(id);
    }

}
