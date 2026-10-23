package com.proyecto.peliculas.dto;

import com.proyecto.peliculas.model.RolActor;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "RepartoRequestDTO",
        description = "Datos opcionales al asignar un actor a una película"
)
public record RepartoRequestDTO(
        @Schema(
                description = "Rol del actor en la película. Si no se indica, será SECUNDARIO",
                example = "VILLANO"
        )
        RolActor rol
) {
}
