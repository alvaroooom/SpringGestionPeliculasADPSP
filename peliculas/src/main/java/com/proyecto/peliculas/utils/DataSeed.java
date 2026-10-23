package com.proyecto.peliculas.utils;

import com.proyecto.peliculas.model.Actor;
import com.proyecto.peliculas.model.Director;
import com.proyecto.peliculas.model.Pelicula;
import com.proyecto.peliculas.model.Reparto;
import com.proyecto.peliculas.model.RolActor;
import com.proyecto.peliculas.repo.ActorRepository;
import com.proyecto.peliculas.repo.DirectorRepository;
import com.proyecto.peliculas.repo.PeliculaRepository;
import com.proyecto.peliculas.repo.RepartoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Carga unos datos de ejemplo al arrancar la aplicación, solo si la base de datos está vacía.
 */
@Component
@RequiredArgsConstructor
public class DataSeed implements CommandLineRunner {

    private final DirectorRepository directorRepository;
    private final PeliculaRepository peliculaRepository;
    private final ActorRepository actorRepository;
    private final RepartoRepository repartoRepository;

    @Override
    public void run(String... args) {
        if (directorRepository.count() > 0) {
            return;
        }

        Director nolan = directorRepository.save(director("Christopher Nolan", 1970));
        Director gerwig = directorRepository.save(director("Greta Gerwig", 1983));
        Director delToro = directorRepository.save(director("Guillermo del Toro", 1964));
        Director almodovar = directorRepository.save(director("Pedro Almodóvar", 1949));

        Pelicula inception = peliculaRepository.save(
                pelicula("Origen", "Ciencia ficción", LocalDate.of(2010, 7, 16), nolan));
        Pelicula oppenheimer = peliculaRepository.save(
                pelicula("Oppenheimer", "Drama", LocalDate.of(2023, 7, 21), nolan));
        Pelicula barbie = peliculaRepository.save(
                pelicula("Barbie", "Comedia", LocalDate.of(2023, 7, 21), gerwig));
        Pelicula formaAgua = peliculaRepository.save(
                pelicula("La forma del agua", "Fantasía", LocalDate.of(2017, 12, 1), delToro));
        Pelicula doloryGloria = peliculaRepository.save(
                pelicula("Dolor y gloria", "Drama", LocalDate.of(2019, 3, 22), almodovar));

        Actor dicaprio = actorRepository.save(actor("Leonardo DiCaprio"));
        Actor murphy = actorRepository.save(actor("Cillian Murphy"));
        Actor robbie = actorRepository.save(actor("Margot Robbie"));
        Actor gosling = actorRepository.save(actor("Ryan Gosling"));
        Actor hawkins = actorRepository.save(actor("Sally Hawkins"));
        Actor banderas = actorRepository.save(actor("Antonio Banderas"));

        reparto(inception, dicaprio, RolActor.PROTAGONISTA);
        reparto(inception, murphy, RolActor.SECUNDARIO);
        reparto(oppenheimer, murphy, RolActor.PROTAGONISTA);
        reparto(barbie, robbie, RolActor.PROTAGONISTA);
        reparto(barbie, gosling, RolActor.SECUNDARIO);
        reparto(formaAgua, hawkins, RolActor.PROTAGONISTA);
        reparto(doloryGloria, banderas, RolActor.PROTAGONISTA);
    }

    private Director director(String nombre, int anioNacimiento) {
        return Director.builder().nombre(nombre).anioNacimiento(anioNacimiento).build();
    }

    private Pelicula pelicula(String titulo, String genero, LocalDate fechaEstreno, Director director) {
        return Pelicula.builder()
                .titulo(titulo)
                .genero(genero)
                .fechaEstreno(fechaEstreno)
                .director(director)
                .build();
    }

    private Actor actor(String nombre) {
        return Actor.builder().nombre(nombre).build();
    }

    private void reparto(Pelicula pelicula, Actor actor, RolActor rol) {
        repartoRepository.save(Reparto.builder()
                .pelicula(pelicula)
                .actor(actor)
                .rol(rol)
                .build());
    }
}
