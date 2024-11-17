package org.example.movie.entites.Genre.controllers.impl;

import org.example.movie.entites.Genre.controllers.api.GenreController;
import org.example.movie.entites.Genre.dto.PutGenreRequest;
import org.example.movie.entites.Genre.functions.PutGenreRequestFunction;
import org.example.movie.entites.Genre.services.api.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class GenreDefaultController implements GenreController {
    private final GenreService genreService;


    private final PutGenreRequestFunction putGenreRequestFunction;

    @Autowired
    public GenreDefaultController(
            GenreService genreService,
            PutGenreRequestFunction putGenreRequestFunction
    ){
        this.genreService = genreService;
        this.putGenreRequestFunction = putGenreRequestFunction;
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
                        () -> genreService.create(putGenreRequestFunction.apply(genreId, putGenreRequest))
                );
    }
}
