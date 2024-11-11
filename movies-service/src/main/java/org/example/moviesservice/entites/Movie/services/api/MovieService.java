package org.example.moviesservice.entites.Movie.services.api;

import org.example.moviesservice.entites.Movie.Movie;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieService {
    Optional<Movie> findById(UUID id);

    List<Movie> findAll();

    Optional<List<Movie>> findAllByGenreId(String genreName);

    Movie create(Movie movie, String genreName) throws IllegalArgumentException;

    Movie create(Movie movie);

    Movie update(Movie movie);

    void delete(UUID id);
}
