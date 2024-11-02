package org.example.moviesservice.entites.Movie.functions;

import org.example.moviesservice.entites.Movie.Movie;
import org.example.moviesservice.entites.Movie.dto.GetMoviesResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class MoviesToResponseFunction implements Function<List<Movie>, GetMoviesResponse> {

    @Override
    public GetMoviesResponse apply(List<Movie> movies) {
        return GetMoviesResponse.builder()
                .movies(movies.stream()
                        .map(movie -> GetMoviesResponse.Movie.builder()
                                .id(movie.getId())
                                .title(movie.getTitle())
                                .build())
                        .toList())
                .build();
    }
}
