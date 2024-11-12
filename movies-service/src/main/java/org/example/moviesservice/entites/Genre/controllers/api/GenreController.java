package org.example.moviesservice.entites.Genre.controllers.api;

import org.example.moviesservice.entites.Genre.dto.GetGenreResponse;
import org.example.moviesservice.entites.Genre.dto.GetGenresResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


public interface GenreController {
    @GetMapping("/api/genres")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetGenresResponse getGenres();

    @GetMapping("/api/genre/{genreId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetGenreResponse getGenre(
            @PathVariable
            String genreId
    );

    @DeleteMapping("/api/genre/{genreId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteGenre(
            @PathVariable
            String genreId
    );
}
