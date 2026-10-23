package com.proyecto.peliculas.controller;

import com.proyecto.peliculas.dto.DirectorRequestDTO;
import com.proyecto.peliculas.dto.DirectorResponseDTO;
import com.proyecto.peliculas.model.Director;
import com.proyecto.peliculas.service.DirectorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/directores")
@Validated
@Tag(name = "Director Controller", description = "Gestión de directores")
public class DirectorController {

    private final DirectorService directorService;

    @Operation(
            summary = "Obtener todos los directores",
            description = "Permite obtener todos los directores. Si no hay ninguno, devuelve una lista vacía"
    )
    @ApiResponse(responseCode = "200", description = "Listado de directores")
    @GetMapping
    public ResponseEntity<List<DirectorResponseDTO>> getAll() {
        return ResponseEntity.ok(
                directorService.getAll()
                        .stream()
                        .map(DirectorResponseDTO::of)
                        .toList());
    }

    @Operation(
            summary = "Obtener un director por su id",
            description = "Permite obtener el detalle de un director a partir de su identificador"
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Director encontrado"),
                    @ApiResponse(responseCode = "404", description = "No existe un director con ese id")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<DirectorResponseDTO> getById(@PathVariable @Min(value = 1) Long id) {
        return ResponseEntity.ok(DirectorResponseDTO.of(directorService.getById(id)));
    }

    @Operation(
            summary = "Crear un director",
            description = "Permite crear un nuevo director"
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "201", description = "Director creado"),
                    @ApiResponse(responseCode = "400", description = "Los datos del director no son válidos")
            }
    )
    @PostMapping
    public ResponseEntity<DirectorResponseDTO> create(@Valid @RequestBody DirectorRequestDTO dto) {
        Director creado = directorService.create(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creado.getId())
                .toUri();

        return ResponseEntity.created(location).body(DirectorResponseDTO.of(creado));
    }

    @Operation(
            summary = "Actualizar un director",
            description = "Permite modificar los datos de un director existente"
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Director actualizado"),
                    @ApiResponse(responseCode = "400", description = "Los datos del director no son válidos"),
                    @ApiResponse(responseCode = "404", description = "No existe un director con ese id")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<DirectorResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody DirectorRequestDTO dto) {
        return ResponseEntity.ok(DirectorResponseDTO.of(directorService.update(id, dto)));
    }

    @Operation(
            summary = "Eliminar un director",
            description = "Permite eliminar un director a partir de su identificador"
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "204", description = "Director eliminado"),
                    @ApiResponse(responseCode = "404", description = "No existe un director con ese id")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        directorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
