package org.example.moviesservice.entites.Genre.services.api;

import org.example.moviesservice.entites.Genre.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    Optional<Genre> findByName(String name);

    List<Genre> findAll();

    Genre create(Genre genre);

    Genre update(Genre genre);

    void delete(String name);
}
