package org.example.movie.entites.Movie.services.api;

import org.example.movie.entites.Movie.Movie;
import org.example.movie.entites.Movie.dto.PatchMovieRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieService {
    Optional<Movie> findById(UUID id);

    List<Movie> findAll();

    Optional<List<Movie>> findAllByGenreId(String genreName);

    void create(Movie movie);

    void update(Movie movie);

    void delete(UUID id);
}
