package org.example.movie.entites.Movie.functions;

import org.example.movie.entites.Genre.Genre;
import org.example.movie.entites.Movie.Movie;
import org.example.movie.entites.Movie.dto.PatchMovieRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class UpdateMovieRequestFunction implements BiFunction<Movie, PatchMovieRequest, Movie> {

    @Override
    public Movie apply(Movie movie, PatchMovieRequest request) {
        return Movie.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .year(movie.getYear())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .genre(movie.getGenre())
                .build();
    }
}
