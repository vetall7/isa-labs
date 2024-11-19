package org.example.movie.entites.Genre.functions;

import org.example.movie.entites.Genre.Genre;
import org.example.movie.entites.Genre.dto.PutGenreRequest;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;
import java.util.function.Function;

@Component
public class PutGenreRequestFunction implements Function<String, Genre> {

    @Override
    public Genre apply(String genreName) {
        return Genre.builder()
                .name(genreName)
                .build();
    }
}
