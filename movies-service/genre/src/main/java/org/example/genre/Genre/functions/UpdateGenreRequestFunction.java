package org.example.genre.Genre.functions;

import org.example.genre.Genre.Genre;
import org.example.genre.Genre.dto.PatchGenreRequest;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
public class UpdateGenreRequestFunction implements BiFunction<Genre, PatchGenreRequest, Genre> {

    @Override
    public Genre apply(Genre genre, PatchGenreRequest request) {
        return Genre.builder()
                .name(genre.getName())
                .description(request.getDescription())
                .build();
    }
}
