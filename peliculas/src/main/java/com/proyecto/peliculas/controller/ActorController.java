package com.proyecto.peliculas.controller;

import com.proyecto.peliculas.dto.ActorRequestDTO;
import com.proyecto.peliculas.dto.ActorResponseDTO;
import com.proyecto.peliculas.model.Actor;
import com.proyecto.peliculas.service.ActorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/actores")
@Validated
@Tag(name = "Actor Controller", description = "Gestión de actores")
public class ActorController {

    private final ActorService actorService;

    @Operation(
            summary = "Obtener todos los actores",
            description = "Permite obtener todos los actores. Si no hay ninguno, devuelve una lista vacía"
    )
    @ApiResponse(responseCode = "200", description = "Listado de actores")
    @GetMapping
    public ResponseEntity<List<ActorResponseDTO>> getAll() {
        return ResponseEntity.ok(
                actorService.getAll()
                        .stream()
                        .map(ActorResponseDTO::of)
                        .toList());
    }

    @Operation(
            summary = "Crear un actor",
            description = "Permite crear un nuevo actor"
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "201", description = "Actor creado"),
                    @ApiResponse(responseCode = "400", description = "Los datos del actor no son válidos")
            }
    )
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
