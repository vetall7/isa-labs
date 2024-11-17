package org.example.movie.entites.Genre.functions;

import org.example.movie.entites.Genre.Genre;
import org.example.movie.entites.Genre.dto.PutGenreRequest;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
public class PutGenreRequestFunction implements BiFunction<String, PutGenreRequest, Genre> {

    @Override
    public Genre apply(String genreName, PutGenreRequest request) {
        return Genre.builder()
                .name(genreName)
                .build();
    }
}
