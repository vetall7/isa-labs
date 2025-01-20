package org.example.movie.entites.Movie.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class GetMoviesResponse {

    @Getter
    @Setter
    @Builder
    @ToString
    @EqualsAndHashCode
    public static class Movie {
        private UUID id;

        private String title;
    }

    @Singular
    private List<Movie> movies;
}
