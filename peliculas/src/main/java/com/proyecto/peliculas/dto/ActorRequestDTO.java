package com.proyecto.peliculas.dto;

import com.proyecto.peliculas.model.Actor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(
        name = "ActorRequestDTO",
        description = "Datos necesarios para crear un actor"
)
public record ActorRequestDTO(
        @Schema(
                description = "Nombre del actor",
                example = "Cillian Murphy"
        )
        @NotBlank(message = "{actorRequestDTO.nombre.notBlank}")
        @Size(max = 100, message = "{actorRequestDTO.nombre.size}")
        String nombre
) {

    public Actor to() {
        return Actor.builder()
                .nombre(nombre)
                .build();
    }
}
