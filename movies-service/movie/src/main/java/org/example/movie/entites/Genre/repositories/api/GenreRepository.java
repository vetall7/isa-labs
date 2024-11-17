package org.example.movie.entites.Genre.repositories.api;

import org.example.movie.entites.Genre.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GenreRepository extends JpaRepository<Genre, String> {
}
