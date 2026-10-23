package com.proyecto.peliculas.service;

import com.proyecto.peliculas.dto.DirectorRequestDTO;
import com.proyecto.peliculas.error.notfound.DirectorNoEncontradoException;
import com.proyecto.peliculas.model.Director;
import com.proyecto.peliculas.repo.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectorService {

    private final DirectorRepository directorRepository;

    public List<Director> getAll() {
        return directorRepository.findAll();
    }

    public Director getById(Long id) {
        return directorRepository.findById(id)
                .orElseThrow(() -> new DirectorNoEncontradoException(id));
    }

    public Director create(DirectorRequestDTO dto) {
        return directorRepository.save(dto.to());
    }

    public Director update(Long id, DirectorRequestDTO dto) {
        Director director = getById(id);
        director.setNombre(dto.nombre());
        director.setAnioNacimiento(dto.anioNacimiento());
        return directorRepository.save(director);
    }

    public void delete(Long id) {
        directorRepository.delete(getById(id));
    }
}
