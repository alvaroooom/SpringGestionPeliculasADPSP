package com.proyecto.peliculas.service;

import com.proyecto.peliculas.dto.ActorRequestDTO;
import com.proyecto.peliculas.error.notfound.ActorNoEncontradoException;
import com.proyecto.peliculas.model.Actor;
import com.proyecto.peliculas.repo.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;

    public List<Actor> getAll() {
        return actorRepository.findAll();
    }

    public Actor getById(Long id) {
        return actorRepository.findById(id)
                .orElseThrow(() -> new ActorNoEncontradoException(id));
    }

    public Actor create(ActorRequestDTO dto) {
        return actorRepository.save(dto.to());
    }
}
