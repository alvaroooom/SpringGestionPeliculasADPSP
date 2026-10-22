package com.proyecto.peliculas.repo;

import com.proyecto.peliculas.model.Reparto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepartoRepository extends JpaRepository<Reparto, Long> {

    boolean existsByPeliculaIdAndActorId(Long peliculaId, Long actorId);
}
