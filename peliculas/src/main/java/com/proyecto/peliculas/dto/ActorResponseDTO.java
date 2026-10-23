package com.proyecto.peliculas.dto;

import com.proyecto.peliculas.model.Actor;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "ActorResponseDTO",
        description = "Detalle de un actor"
)
public record ActorResponseDTO(
        @Schema(
                description = "Identificador único del actor",
                example = "2"
        )
        Long id,

        @Schema(
                description = "Nombre del actor",
                example = "Cillian Murphy"
        )
        String nombre
) {

    public static ActorResponseDTO of(Actor actor) {
        return new ActorResponseDTO(actor.getId(), actor.getNombre());
    }
}
