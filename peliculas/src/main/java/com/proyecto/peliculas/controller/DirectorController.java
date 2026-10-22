package com.proyecto.peliculas.controller;

import com.proyecto.peliculas.dto.DirectorRequestDTO;
import com.proyecto.peliculas.dto.DirectorResponseDTO;
import com.proyecto.peliculas.model.Director;
import com.proyecto.peliculas.service.DirectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/directores")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping
    public List<DirectorResponseDTO> getAll() {
        return directorService.getAll().stream()
                .map(DirectorResponseDTO::of)
                .toList();
    }

    @GetMapping("/{id}")
    public DirectorResponseDTO getById(@PathVariable Long id) {
        return DirectorResponseDTO.of(directorService.getById(id));
    }

    @PostMapping
    public ResponseEntity<DirectorResponseDTO> create(@Valid @RequestBody DirectorRequestDTO dto) {
        Director creado = directorService.create(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creado.getId())
                .toUri();

        return ResponseEntity.created(location).body(DirectorResponseDTO.of(creado));
    }

    @PutMapping("/{id}")
    public DirectorResponseDTO update(@PathVariable Long id, @Valid @RequestBody DirectorRequestDTO dto) {
        return DirectorResponseDTO.of(directorService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        directorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
