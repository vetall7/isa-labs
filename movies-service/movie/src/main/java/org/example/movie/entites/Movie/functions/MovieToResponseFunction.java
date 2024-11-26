package org.example.movie.entites.Movie.functions;

import org.example.movie.entites.Movie.Movie;
import org.example.movie.entites.Movie.dto.GetMovieResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MovieToResponseFunction implements Function<Movie, GetMovieResponse> {

    @Override
    public GetMovieResponse apply(Movie movie) {
        return GetMovieResponse.builder()
                .title(movie.getTitle())
                .description(movie.getDescription())
                .imageUrl(movie.getImageUrl())
                .genreName(movie.getGenre().getName())
                .year(movie.getYear())
                .build();
    }
}
