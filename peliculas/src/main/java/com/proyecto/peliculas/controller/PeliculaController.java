package com.proyecto.peliculas.controller;

import com.proyecto.peliculas.dto.PeliculaRequestDTO;
import com.proyecto.peliculas.dto.PeliculaResponseDTO;
import com.proyecto.peliculas.dto.RepartoRequestDTO;
import com.proyecto.peliculas.model.Pelicula;
import com.proyecto.peliculas.model.RolActor;
import com.proyecto.peliculas.service.PeliculaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/peliculas")
@Validated
@Tag(name = "Pelicula Controller", description = "Gestión de películas y de su reparto de actores")
public class PeliculaController {

    private final PeliculaService peliculaService;

    @Operation(
            summary = "Obtener todas las películas",
            description = "Permite obtener todas las películas con su director y su reparto. Si no hay ninguna, devuelve una lista vacía"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Listado de películas",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = PeliculaResponseDTO.class
                                            )
                                    ),
                                    examples = @ExampleObject(
                                            name = "Listado de películas",
                                            summary = "Ejemplo de listado de películas",
                                            value = """
                                                    [
                                                      {
                                                        "id": 1,
                                                        "titulo": "Origen",
                                                        "genero": "Ciencia ficción",
                                                        "fechaEstreno": "2010-07-16",
                                                        "director": {
                                                          "id": 1,
                                                          "nombre": "Christopher Nolan"
                                                        },
                                                        "reparto": [
                                                          {
                                                            "actor": {
                                                              "id": 1,
                                                              "nombre": "Leonardo DiCaprio"
                                                            },
                                                            "rol": "PROTAGONISTA"
                                                          }
                                                        ]
                                                      }
                                                    ]
                                                    """
                                    )
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<PeliculaResponseDTO>> getAll() {
        return ResponseEntity.ok(
                peliculaService.getAll()
                        .stream()
                        .map(PeliculaResponseDTO::of)
                        .toList());
    }

    @Operation(
            summary = "Obtener una película por su id",
            description = "Permite obtener el detalle de una película, incluyendo su director y la lista de su reparto de actores"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Película encontrada",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = PeliculaResponseDTO.class
                                    ),
                                    examples = @ExampleObject(
                                            name = "Película",
                                            summary = "Ejemplo de película con su reparto",
                                            value = """
                                                    {
                                                      "id": 1,
                                                      "titulo": "Origen",
                                                      "genero": "Ciencia ficción",
                                                      "fechaEstreno": "2010-07-16",
                                                      "director": {
                                                        "id": 1,
                                                        "nombre": "Christopher Nolan"
                                                      },
                                                      "reparto": [
                                                        {
                                                          "actor": {
                                                            "id": 1,
                                                            "nombre": "Leonardo DiCaprio"
                                                          },
                                                          "rol": "PROTAGONISTA"
                                                        },
                                                        {
                                                          "actor": {
                                                            "id": 2,
                                                            "nombre": "Cillian Murphy"
                                                          },
                                                          "rol": "SECUNDARIO"
                                                        }
                                                      ]
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No existe una película con ese id",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(
                                            implementation = ProblemDetail.class
                                    ),
                                    examples = @ExampleObject(
                                            name = "Error 404",
                                            summary = "Mensaje de error informando de que la película no existe",
                                            value = """
                                                    {
                                                      "detail": "No se ha encontrado la película con id 99",
                                                      "instance": "/api/v1/peliculas/99",
                                                      "status": 404,
                                                      "title": "Recurso no encontrado",
                                                      "type": "https://example.com/errors/not-found"
                                                    }
                                                    """
                                    )
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<PeliculaResponseDTO> getById(@PathVariable @Min(value = 1) Long id) {
        return ResponseEntity.ok(PeliculaResponseDTO.of(peliculaService.getById(id)));
    }

    @Operation(
            summary = "Crear una película",
            description = "Permite crear una película. Requiere el id de un director existente, que debe tener al menos 18 años en el año de estreno"
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "201", description = "Película creada"),
                    @ApiResponse(responseCode = "400", description = "Datos no válidos, o el director no tiene 18 años en el año de estreno"),
                    @ApiResponse(responseCode = "404", description = "El director indicado no existe"),
                    @ApiResponse(responseCode = "409", description = "Ya existe una película con ese título")
            }
    )
    @PostMapping
    public ResponseEntity<PeliculaResponseDTO> create(@Valid @RequestBody PeliculaRequestDTO dto) {
        Pelicula creada = peliculaService.create(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creada.getId())
                .toUri();

        return ResponseEntity.created(location).body(PeliculaResponseDTO.of(creada));
    }

    @Operation(
            summary = "Asignar un actor a una película",
            description = "Añade un actor al reparto de una película. El cuerpo es opcional: si no se indica rol, el actor será SECUNDARIO"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Actor asignado. Devuelve la película con su reparto actualizado",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = PeliculaResponseDTO.class
                                    ),
                                    examples = @ExampleObject(
                                            name = "Actor asignado",
                                            summary = "Ejemplo de película tras asignarle un actor",
                                            value = """
                                                    {
                                                      "id": 1,
                                                      "titulo": "Origen",
                                                      "genero": "Ciencia ficción",
                                                      "fechaEstreno": "2010-07-16",
                                                      "director": {
                                                        "id": 1,
                                                        "nombre": "Christopher Nolan"
                                                      },
                                                      "reparto": [
                                                        {
                                                          "actor": {
                                                            "id": 3,
                                                            "nombre": "Tom Hardy"
                                                          },
                                                          "rol": "VILLANO"
                                                        }
                                                      ]
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "La película o el actor no existen",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(
                                            implementation = ProblemDetail.class
                                    ),
                                    examples = @ExampleObject(
                                            name = "Error 404",
                                            summary = "Mensaje de error informando de que el actor no existe",
                                            value = """
                                                    {
                                                      "detail": "No se ha encontrado el actor con id 99",
                                                      "instance": "/api/v1/peliculas/1/actores/99",
                                                      "status": 404,
                                                      "title": "Recurso no encontrado",
                                                      "type": "https://example.com/errors/not-found"
                                                    }
                                                    """
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "El actor ya forma parte del reparto de la película",
                            content = @Content(
                                    mediaType = "application/problem+json",
                                    schema = @Schema(
                                            implementation = ProblemDetail.class
                                    ),
                                    examples = @ExampleObject(
                                            name = "Error 409",
                                            summary = "Mensaje de error informando de que el actor ya está en el reparto",
                                            value = """
                                                    {
                                                      "detail": "El actor con id 1 ya forma parte del reparto de la película con id 1",
                                                      "instance": "/api/v1/peliculas/1/actores/1",
                                                      "status": 409,
                                                      "title": "Actor ya asignado",
                                                      "type": "https://example.com/errors/actor-ya-en-reparto"
                                                    }
                                                    """
                                    )
                            )
                    )
            }
    )
    @PostMapping("/{peliculaId}/actores/{actorId}")
    public ResponseEntity<PeliculaResponseDTO> asignarActor(
            @PathVariable Long peliculaId,
            @PathVariable Long actorId,
            @RequestBody(required = false) RepartoRequestDTO dto) {

        RolActor rol = dto != null ? dto.rol() : null;
        Pelicula pelicula = peliculaService.asignarActor(peliculaId, actorId, rol);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/peliculas/{id}")
                .buildAndExpand(peliculaId)
                .toUri();

        return ResponseEntity.created(location).body(PeliculaResponseDTO.of(pelicula));
    }

    @Operation(
            summary = "Actualizar una película",
            description = "Permite modificar los datos de una película existente"
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", description = "Película actualizada"),
                    @ApiResponse(responseCode = "400", description = "Datos no válidos, o el director no tiene 18 años en el año de estreno"),
                    @ApiResponse(responseCode = "404", description = "La película o el director no existen"),
                    @ApiResponse(responseCode = "409", description = "Ya existe otra película con ese título")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<PeliculaResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody PeliculaRequestDTO dto) {
        return ResponseEntity.ok(PeliculaResponseDTO.of(peliculaService.update(id, dto)));
    }

    @Operation(
            summary = "Eliminar una película",
            description = "Permite eliminar una película, junto con su reparto"
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "204", description = "Película eliminada"),
                    @ApiResponse(responseCode = "404", description = "No existe una película con ese id")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        peliculaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
