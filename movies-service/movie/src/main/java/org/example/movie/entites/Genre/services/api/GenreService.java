package org.example.movie.entites.Genre.services.api;

import org.example.movie.entites.Genre.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    Optional<Genre> findByName(String name);

    void create(Genre genre);

    void delete(String name);
}
