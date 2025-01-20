package org.example.movie.entites.Movie.functions;

import org.example.movie.entites.Genre.Genre;
import org.example.movie.entites.Movie.Movie;
import org.example.movie.entites.Movie.dto.PutMovieRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class RequestToMovieFunction implements BiFunction<UUID, PutMovieRequest, Movie> {

    @Override
    public Movie apply(UUID id, PutMovieRequest request) {
        return Movie.builder()
                .id(id)
                .title(request.getTitle())
                .year(request.getYear())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .genre(Genre.builder()
                        .name(request.getGenreName())
                        .build())
                .build();
    }
}
