package org.example.movie.entites.Movie.repositories.api;

import org.example.movie.entites.Movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {
    List<Movie> findAllByGenreName(String genreName);
}
