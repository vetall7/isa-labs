package org.example.entities;

import java.util.function.Function;

public class MovieToDto implements Function<Movie, MovieDto> {

    @Override
    public MovieDto apply(Movie movie) {
        return MovieDto.builder()
                .title(movie.getTitle())
                .description(movie.getDescription())
                .imageUrl(movie.getImageUrl())
                .year(movie.getYear())
                .genreName(movie.getGenre().getName())
                .build();
    }
}
