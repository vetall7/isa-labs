package org.example.moviesservice.entites.Movie.functions;

import org.example.moviesservice.entites.Movie.Movie;
import org.example.moviesservice.entites.Movie.dto.GetMovieResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MovieToResponseFunction implements Function<Movie, GetMovieResponse> {

    @Override
    public GetMovieResponse apply(Movie movie) {
        return GetMovieResponse.builder()
                .title(movie.getTitle())
                .year(movie.getYear())
                .genreName(movie.getGenre().getName())
                .build();
    }
}
