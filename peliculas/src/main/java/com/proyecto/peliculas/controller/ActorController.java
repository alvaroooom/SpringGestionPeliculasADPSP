package com.proyecto.peliculas.controller;

import com.proyecto.peliculas.dto.ActorRequestDTO;
import com.proyecto.peliculas.dto.ActorResponseDTO;
import com.proyecto.peliculas.model.Actor;
import com.proyecto.peliculas.service.ActorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/actores")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService actorService;

    @GetMapping
    public List<ActorResponseDTO> getAll() {
        return actorService.getAll().stream()
                .map(ActorResponseDTO::of)
                .toList();
    }

    @PostMapping
    public ResponseEntity<ActorResponseDTO> create(@Valid @RequestBody ActorRequestDTO dto) {
        Actor creado = actorService.create(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creado.getId())
                .toUri();

        return ResponseEntity.created(location).body(ActorResponseDTO.of(creado));
    }
}
