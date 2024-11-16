package org.example.moviesservice.entites.Genre.functions;

import org.example.moviesservice.entites.Genre.Genre;
import org.example.moviesservice.entites.Genre.dto.PatchGenreRequest;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
public class UpdateGenreRequestFunction implements BiFunction<Genre, PatchGenreRequest, Genre> {

    @Override
    public Genre apply(Genre genre, PatchGenreRequest request) {
        return Genre.builder()
                .name(genre.getName())
                .description(request.getDescription())
                .movies(genre.getMovies())
                .build();
    }
}
