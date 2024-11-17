package org.example.movie.entites.Movie.services.impl;

import org.example.movie.entites.Genre.repositories.api.GenreRepository;
import org.example.movie.entites.Movie.Movie;
import org.example.movie.entites.Movie.dto.PatchMovieRequest;
import org.example.movie.entites.Movie.repositories.api.MovieRepository;
import org.example.movie.entites.Movie.services.api.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovieDefaultService implements MovieService {
    private final MovieRepository movieRepository;

    private final GenreRepository genreRepository;

    @Autowired
    public MovieDefaultService(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public Optional<Movie> findById(UUID id) {
        return movieRepository.findById(id);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<List<Movie>> findAllByGenreId(String genreName) {
        return genreRepository.findById(genreName)
                .map(genre -> movieRepository.findAllByGenreName(genreName));
    }

    @Override
    public void create(Movie movie) {
        genreRepository.findById(movie.getGenre().getName())
                .ifPresentOrElse(
                        genre -> movieRepository.save(movie),
                        () -> {
                            throw new RuntimeException("Genre does not exist");
                        }
                );
    }

    @Override
    public void update(Movie movie) {
        movieRepository.save(movie);
    }

    @Override
    public void delete(UUID id) {
        movieRepository.deleteById(id);
    }
}
