package com.proyecto.peliculas.service;

import com.proyecto.peliculas.dto.PeliculaRequestDTO;
import com.proyecto.peliculas.error.ActorYaEnRepartoException;
import com.proyecto.peliculas.error.PeliculaYaExisteException;
import com.proyecto.peliculas.error.notfound.PeliculaNoEncontradaException;
import com.proyecto.peliculas.model.Actor;
import com.proyecto.peliculas.model.Director;
import com.proyecto.peliculas.model.Pelicula;
import com.proyecto.peliculas.model.Reparto;
import com.proyecto.peliculas.model.RolActor;
import com.proyecto.peliculas.repo.PeliculaRepository;
import com.proyecto.peliculas.repo.RepartoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;
    private final RepartoRepository repartoRepository;
    private final DirectorService directorService;
    private final ActorService actorService;

    public List<Pelicula> getAll() {
        return peliculaRepository.findAll();
    }

    public Pelicula getById(Long id) {
        return peliculaRepository.findById(id)
                .orElseThrow(() -> new PeliculaNoEncontradaException(id));
    }

    public Pelicula create(PeliculaRequestDTO dto) {
        if (peliculaRepository.existsByTituloIgnoreCase(dto.titulo())) {
            throw new PeliculaYaExisteException(dto.titulo());
        }

        Director director = directorService.getById(dto.directorId());

        Pelicula pelicula = Pelicula.builder()
                .titulo(dto.titulo())
                .genero(dto.genero())
                .fechaEstreno(dto.fechaEstreno())
                .director(director)
                .build();

        return peliculaRepository.save(pelicula);
    }

    public Pelicula update(Long id, PeliculaRequestDTO dto) {
        Pelicula pelicula = getById(id);

        if (peliculaRepository.existsByTituloIgnoreCaseAndIdNot(dto.titulo(), id)) {
            throw new PeliculaYaExisteException(dto.titulo());
        }

        pelicula.setTitulo(dto.titulo());
        pelicula.setGenero(dto.genero());
        pelicula.setFechaEstreno(dto.fechaEstreno());
        pelicula.setDirector(directorService.getById(dto.directorId()));

        return peliculaRepository.save(pelicula);
    }

    public void delete(Long id) {
        peliculaRepository.delete(getById(id));
    }

    @Transactional
    public Pelicula asignarActor(Long peliculaId, Long actorId, RolActor rol) {
        Pelicula pelicula = getById(peliculaId);
        Actor actor = actorService.getById(actorId);

        if (repartoRepository.existsByPeliculaIdAndActorId(peliculaId, actorId)) {
            throw new ActorYaEnRepartoException(peliculaId, actorId);
        }

        Reparto reparto = Reparto.builder()
                .pelicula(pelicula)
                .actor(actor)
                .rol(rol != null ? rol : RolActor.SECUNDARIO)
                .build();

        pelicula.getReparto().add(repartoRepository.save(reparto));

        return pelicula;
    }
}
