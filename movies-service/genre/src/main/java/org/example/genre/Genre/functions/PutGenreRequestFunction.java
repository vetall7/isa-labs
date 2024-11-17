package org.example.genre.Genre.functions;

import org.example.genre.Genre.Genre;
import org.example.genre.Genre.dto.PutGenreRequest;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
public class PutGenreRequestFunction implements BiFunction<String, PutGenreRequest, Genre> {

    @Override
    public Genre apply(String genreName, PutGenreRequest request) {
        return Genre.builder()
                .name(genreName)
                .description(request.getDescription())
                .build();
    }
}
