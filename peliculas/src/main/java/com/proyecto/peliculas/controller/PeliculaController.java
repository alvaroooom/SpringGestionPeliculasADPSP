package com.proyecto.peliculas.controller;

import com.proyecto.peliculas.dto.PeliculaRequestDTO;
import com.proyecto.peliculas.dto.PeliculaResponseDTO;
import com.proyecto.peliculas.model.Pelicula;
import com.proyecto.peliculas.service.PeliculaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/peliculas")
@RequiredArgsConstructor
public class PeliculaController {

    private final PeliculaService peliculaService;

    @GetMapping
    public List<PeliculaResponseDTO> getAll() {
        return peliculaService.getAll().stream()
                .map(PeliculaResponseDTO::of)
                .toList();
    }

    @GetMapping("/{id}")
    public PeliculaResponseDTO getById(@PathVariable Long id) {
        return PeliculaResponseDTO.of(peliculaService.getById(id));
    }

    @PostMapping
    public ResponseEntity<PeliculaResponseDTO> create(@Valid @RequestBody PeliculaRequestDTO dto) {
        Pelicula creada = peliculaService.create(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creada.getId())
                .toUri();

        return ResponseEntity.created(location).body(PeliculaResponseDTO.of(creada));
    }

    @PutMapping("/{id}")
    public PeliculaResponseDTO update(@PathVariable Long id, @Valid @RequestBody PeliculaRequestDTO dto) {
        return PeliculaResponseDTO.of(peliculaService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        peliculaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
