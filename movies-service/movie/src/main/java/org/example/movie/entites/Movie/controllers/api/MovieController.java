package org.example.movie.entites.Movie.controllers.api;

import org.example.movie.entites.Movie.dto.GetMovieResponse;
import org.example.movie.entites.Movie.dto.GetMoviesResponse;
import org.example.movie.entites.Movie.dto.PatchMovieRequest;
import org.example.movie.entites.Movie.dto.PutMovieRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public interface MovieController {

    @GetMapping("/api/movies")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetMoviesResponse getMovies();

    @GetMapping("/api/movies/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetMovieResponse getMovie(
            @PathVariable("id")
            UUID movieId
    );

    @GetMapping("/api/genres/{genreId}/movies")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetMoviesResponse getGenreMovies(
            @PathVariable("genreId")
            String genreId
    );

    @PutMapping("/api/movies/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    void putMovie(
            @PathVariable("id")
            UUID movieId,
            @RequestBody
            PutMovieRequest putMovieRequest
    );

    @DeleteMapping("/api/movies/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteMovie(
            @PathVariable("id")
            UUID movieId
    );

    @PatchMapping("/api/movies/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateMovie(
            @PathVariable("id")
            UUID movieId,
            @RequestBody
            PatchMovieRequest fieldsToUpdate
    );
}
