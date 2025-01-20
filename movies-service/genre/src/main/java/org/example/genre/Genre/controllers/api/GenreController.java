package org.example.genre.Genre.controllers.api;

import org.example.genre.Genre.dto.GetGenreResponse;
import org.example.genre.Genre.dto.GetGenresResponse;
import org.example.genre.Genre.dto.PatchGenreRequest;
import org.example.genre.Genre.dto.PutGenreRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


public interface GenreController {
    @GetMapping("/api/genres")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetGenresResponse getGenres();

    @GetMapping("/api/genres/{genreId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetGenreResponse getGenre(
            @PathVariable
            String genreId
    );

    @PutMapping("/api/genres/{genreId}")
    @ResponseStatus(HttpStatus.CREATED)
    void createGenre(
            @PathVariable
            String genreId,
            @RequestBody
            PutGenreRequest
            putGenreRequest
    );

    @PatchMapping("/api/genres/{genreId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateGenre(
            @PathVariable
            String genreId,
            @RequestBody
            PatchGenreRequest
            fieldsToUpdate
    );

    @DeleteMapping("/api/genres/{genreId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteGenre(
            @PathVariable
            String genreId
    );
}
