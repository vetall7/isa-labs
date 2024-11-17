package org.example.genre.Genre.services.api;

import org.example.genre.Genre.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    Optional<Genre> findByName(String name);

    List<Genre> findAll();

    void create(Genre genre);

    void update(Genre genre);

    void delete(String name);
}