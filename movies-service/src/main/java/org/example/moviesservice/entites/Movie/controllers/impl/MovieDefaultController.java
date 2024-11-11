package org.example.moviesservice.entites.Movie.controllers.impl;

import org.example.moviesservice.entites.Movie.controllers.api.MovieController;
import org.example.moviesservice.entites.Movie.dto.GetMovieResponse;
import org.example.moviesservice.entites.Movie.dto.GetMoviesResponse;
import org.example.moviesservice.entites.Movie.dto.PutMovieRequest;
import org.example.moviesservice.entites.Movie.functions.MovieToResponseFunction;
import org.example.moviesservice.entites.Movie.functions.MoviesToResponseFunction;
import org.example.moviesservice.entites.Movie.functions.RequestToMovieFunction;
import org.example.moviesservice.entites.Movie.functions.UpdateMovieRequestFunction;
import org.example.moviesservice.entites.Movie.services.api.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
public class MovieDefaultController implements MovieController {
    private final MovieService movieService;

    private final MoviesToResponseFunction moviesToResponseFunction;

    private final MovieToResponseFunction movieToResponseFunction;

    private final RequestToMovieFunction requestToMovieFunction;

    private final UpdateMovieRequestFunction updateMovieRequestFunction;

    public MovieDefaultController(MovieService movieService, MoviesToResponseFunction moviesToResponseFunction, MovieToResponseFunction movieToResponseFunction, RequestToMovieFunction requestToMovieFunction, UpdateMovieRequestFunction updateMovieRequestFunction) {
        this.movieService = movieService;
        this.moviesToResponseFunction = moviesToResponseFunction;
        this.movieToResponseFunction = movieToResponseFunction;
        this.requestToMovieFunction = requestToMovieFunction;
        this.updateMovieRequestFunction = updateMovieRequestFunction;
    }

    @Override
    public GetMoviesResponse getMovies() {
        return moviesToResponseFunction.apply(movieService.findAll());
    }

    @Override
    public GetMovieResponse getMovie(UUID movieId) {
        return movieService.findById(movieId)
                .map(movieToResponseFunction)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public GetMoviesResponse getGenreMovies(String genreId) {
        return movieService.findAllByGenreId(genreId)
                .map(moviesToResponseFunction)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void putMovie(UUID movieId, PutMovieRequest request){
        movieService.create(requestToMovieFunction.apply(movieId, request));
    }

    @Override
    public void deleteMovie(UUID movieId){
        movieService.findById(movieId)
                .ifPresentOrElse(
                        movie -> movieService.delete(movieId),
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                        }
                );
    }
}
