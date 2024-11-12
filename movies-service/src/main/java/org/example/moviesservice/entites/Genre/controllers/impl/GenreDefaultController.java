package org.example.moviesservice.entites.Genre.controllers.impl;

import org.example.moviesservice.entites.Genre.controllers.api.GenreController;
import org.example.moviesservice.entites.Genre.dto.GetGenreResponse;
import org.example.moviesservice.entites.Genre.dto.GetGenresResponse;
import org.example.moviesservice.entites.Genre.functions.GenreToResponseFunction;
import org.example.moviesservice.entites.Genre.functions.GenresToResponseFunction;
import org.example.moviesservice.entites.Genre.services.api.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class GenreDefaultController implements GenreController {
    private final GenreService genreService;

    private final GenresToResponseFunction genresToResponseFunction;

    private final GenreToResponseFunction genreToResponseFunction;

    @Autowired
    public GenreDefaultController(
            GenreService genreService,
            GenresToResponseFunction genresToResponseFunction,
            GenreToResponseFunction genreToResponseFunction
    ){
        this.genreService = genreService;
        this.genresToResponseFunction = genresToResponseFunction;
        this.genreToResponseFunction = genreToResponseFunction;
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
}
