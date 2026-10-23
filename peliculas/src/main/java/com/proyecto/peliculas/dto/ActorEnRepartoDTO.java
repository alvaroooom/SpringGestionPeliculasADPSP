package com.proyecto.peliculas.dto;

import com.proyecto.peliculas.model.Reparto;
import com.proyecto.peliculas.model.RolActor;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "ActorEnRepartoDTO",
        description = "Actor que forma parte del reparto de una película, junto con su rol"
)
public record ActorEnRepartoDTO(
        @Schema(description = "Datos del actor")
        ActorSimpleDTO actor,

        @Schema(
                description = "Rol del actor en la película",
                example = "PROTAGONISTA"
        )
        RolActor rol
) {

    public static ActorEnRepartoDTO of(Reparto reparto) {
        return new ActorEnRepartoDTO(ActorSimpleDTO.of(reparto.getActor()), reparto.getRol());
    }
}
