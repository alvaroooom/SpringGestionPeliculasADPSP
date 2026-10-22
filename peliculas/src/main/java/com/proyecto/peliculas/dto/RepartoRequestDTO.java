package com.proyecto.peliculas.dto;

import com.proyecto.peliculas.model.RolActor;

/**
 * Cuerpo opcional al asignar un actor a una película. Si no se indica rol, será SECUNDARIO.
 */
public record RepartoRequestDTO(
        RolActor rol
) {
}
