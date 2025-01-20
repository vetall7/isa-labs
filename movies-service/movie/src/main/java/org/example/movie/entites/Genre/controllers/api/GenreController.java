package org.example.movie.entites.Genre.controllers.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


public interface GenreController {
    @PutMapping("/api/genres/{genreId}")
    @ResponseStatus(HttpStatus.CREATED)
    void createGenre(
            @PathVariable
            String genreId
    );


    @DeleteMapping("/api/genres/{genreId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteGenre(
            @PathVariable
            String genreId
    );
}
