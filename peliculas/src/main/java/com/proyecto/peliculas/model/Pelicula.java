package com.proyecto.peliculas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pelicula {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String titulo;

    private String genero;

    private LocalDate fechaEstreno;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;
}
