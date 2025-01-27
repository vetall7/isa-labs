package org.example.genre.Genre.controllers.impl;

import org.example.genre.Genre.controllers.api.GenreController;
import org.example.genre.Genre.dto.GetGenreResponse;
import org.example.genre.Genre.dto.GetGenresResponse;
import org.example.genre.Genre.dto.PatchGenreRequest;
import org.example.genre.Genre.dto.PutGenreRequest;
import org.example.genre.Genre.functions.GenreToResponseFunction;
import org.example.genre.Genre.functions.GenresToResponseFunction;
import org.example.genre.Genre.functions.PutGenreRequestFunction;
import org.example.genre.Genre.functions.UpdateGenreRequestFunction;
import org.example.genre.Genre.services.api.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class GenreDefaultController implements GenreController {
    private final GenreService genreService;

    private final GenresToResponseFunction genresToResponseFunction;

    private final GenreToResponseFunction genreToResponseFunction;

    private final UpdateGenreRequestFunction updateGenreRequestFunction;

    private final PutGenreRequestFunction putGenreRequestFunction;

    @Autowired
    public GenreDefaultController(
            GenreService genreService,
            GenresToResponseFunction genresToResponseFunction,
            GenreToResponseFunction genreToResponseFunction,
            UpdateGenreRequestFunction updateGenreRequestFunction,
            PutGenreRequestFunction putGenreRequestFunction
    ){
        this.genreService = genreService;
        this.genresToResponseFunction = genresToResponseFunction;
        this.genreToResponseFunction = genreToResponseFunction;
        this.updateGenreRequestFunction = updateGenreRequestFunction;
        this.putGenreRequestFunction = putGenreRequestFunction;
    }

    @Override
    public GetGenresResponse getGenres(){
        return genresToResponseFunction.apply(genreService.findAll());
    }

    @Override
    public GetGenreResponse getGenre(String genreId){
        return genreService.findByName(genreId)
                .map(genreToResponseFunction)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void deleteGenre(String genreId){
        genreService.findByName(genreId)
                .ifPresentOrElse(
                        genre -> genreService.delete(genreId),
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                        }
                );
    }

    @Override
    public void createGenre(String genreId, PutGenreRequest putGenreRequest){
        genreService.findByName(genreId)
                .ifPresentOrElse(
                        genre -> {
                            throw new ResponseStatusException(HttpStatus.CONFLICT);
                        },
                        () -> genreService.create(putGenreRequestFunction.apply(genreId, putGenreRequest), true)
                );
    }

    @Override
    public void updateGenre(String genreId, PatchGenreRequest fieldsToUpdate){
        genreService.findByName(genreId)
                        .ifPresentOrElse(
                                genre -> genreService.update(updateGenreRequestFunction.apply(genre, fieldsToUpdate)),
                                () -> {
                                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                                }
                        );
    }
}
