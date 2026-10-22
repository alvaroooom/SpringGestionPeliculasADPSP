package com.proyecto.peliculas.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Tabla intermedia de la relación N:M entre Película y Actor.
 * Se modela como entidad propia para poder guardar el rol del actor en la película.
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"pelicula_id", "actor_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reparto {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pelicula_id")
    private Pelicula pelicula;

    @ManyToOne(optional = false)
    @JoinColumn(name = "actor_id")
    private Actor actor;

    @Enumerated(EnumType.STRING)
    private RolActor rol;
}
