package org.example.genre.initializer;

import org.example.genre.Genre.Genre;
import org.example.genre.Genre.services.api.GenreService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataInitializer implements InitializingBean {
    private final GenreService genreService;


    @Autowired
    public DataInitializer(GenreService genreService) {
        this.genreService = genreService;
    }

    @Override
    public void afterPropertiesSet() {
        Genre genre1 = Genre.builder().name("Action").description("Action movies").build();
        Genre genre2 = Genre.builder().name("Comedy").description("Comedy movies").build();
        Genre genre3 = Genre.builder().name("Drama").description("Drama movies").build();
        Genre genre4 = Genre.builder().name("Horror").description("Horror movies").build();
        Genre genre5 = Genre.builder().name("Sci-Fi").description("Science Fiction movies").build();

        genreService.create(genre1, false);
        genreService.create(genre2, false);
        genreService.create(genre3, false);
        genreService.create(genre4, false);
        genreService.create(genre5, false);
    }
}
