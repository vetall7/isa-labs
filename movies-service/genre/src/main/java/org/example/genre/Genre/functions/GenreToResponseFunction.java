package org.example.genre.Genre.functions;

import org.example.genre.Genre.Genre;
import org.example.genre.Genre.dto.GetGenreResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class GenreToResponseFunction implements Function<Genre, GetGenreResponse> {
    @Override
    public GetGenreResponse apply(Genre genre){
        return GetGenreResponse.builder()
                .name(genre.getName())
                .description(genre.getDescription())
                .build();
    }
}