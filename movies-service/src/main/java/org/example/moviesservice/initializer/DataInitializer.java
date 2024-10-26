package org.example.moviesservice.initializer;

import org.example.moviesservice.entites.Genre.Genre;
import org.example.moviesservice.entites.Genre.services.api.GenreService;
import org.example.moviesservice.entites.Movie.Movie;
import org.example.moviesservice.entites.Movie.services.api.MovieService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataInitializer implements InitializingBean {
    private final GenreService genreService;

    private final MovieService movieService;

    @Autowired
    public DataInitializer(GenreService genreService, MovieService movieService) {
        this.genreService = genreService;
        this.movieService = movieService;
    }

    @Override
    public void afterPropertiesSet() {
        Genre genre1 = Genre.builder().name("Action").description("Action movies").build();
        Genre genre2 = Genre.builder().name("Comedy").description("Comedy movies").build();
        Genre genre3 = Genre.builder().name("Drama").description("Drama movies").build();
        Genre genre4 = Genre.builder().name("Horror").description("Horror movies").build();
        Genre genre5 = Genre.builder().name("Sci-Fi").description("Science Fiction movies").build();

        genreService.create(genre1);
        genreService.create(genre2);
        genreService.create(genre3);
        genreService.create(genre4);
        genreService.create(genre5);

        Movie movie1 = Movie.builder().id(UUID.randomUUID()).title("Action Movie 1").description("An action movie").imageUrl("url1").year(2021).genre(genre1).build();
        Movie movie2 = Movie.builder().id(UUID.randomUUID()).title("Action Movie 2").description("An action movie").imageUrl("url2").year(2022).genre(genre1).build();
        Movie movie3 = Movie.builder().id(UUID.randomUUID()).title("Comedy Movie 1").description("A comedy movie").imageUrl("url3").year(2021).genre(genre2).build();
        Movie movie4 = Movie.builder().id(UUID.randomUUID()).title("Comedy Movie 2").description("A comedy movie").imageUrl("url4").year(2022).genre(genre2).build();
        Movie movie5 = Movie.builder().id(UUID.randomUUID()).title("Drama Movie 1").description("A drama movie").imageUrl("url5").year(2021).genre(genre3).build();
        Movie movie6 = Movie.builder().id(UUID.randomUUID()).title("Drama Movie 2").description("A drama movie").imageUrl("url6").year(2022).genre(genre3).build();
        Movie movie7 = Movie.builder().id(UUID.randomUUID()).title("Horror Movie 1").description("A horror movie").imageUrl("url7").year(2021).genre(genre4).build();
        Movie movie8 = Movie.builder().id(UUID.randomUUID()).title("Horror Movie 2").description("A horror movie").imageUrl("url8").year(2022).genre(genre4).build();
        Movie movie9 = Movie.builder().id(UUID.randomUUID()).title("Sci-Fi Movie 1").description("A sci-fi movie").imageUrl("url9").year(2021).genre(genre5).build();
        Movie movie10 = Movie.builder().id(UUID.randomUUID()).title("Sci-Fi Movie 2").description("A sci-fi movie").imageUrl("url10").year(2022).genre(genre5).build();
        Movie movie11 = Movie.builder().id(UUID.randomUUID()).title("Action Movie 3").description("An action movie").imageUrl("url11").year(2023).genre(genre1).build();
        Movie movie12 = Movie.builder().id(UUID.randomUUID()).title("Comedy Movie 3").description("A comedy movie").imageUrl("url12").year(2023).genre(genre2).build();
        Movie movie13 = Movie.builder().id(UUID.randomUUID()).title("Drama Movie 3").description("A drama movie").imageUrl("url13").year(2023).genre(genre3).build();
        Movie movie14 = Movie.builder().id(UUID.randomUUID()).title("Horror Movie 3").description("A horror movie").imageUrl("url14").year(2023).genre(genre4).build();
        Movie movie15 = Movie.builder().id(UUID.randomUUID()).title("Sci-Fi Movie 3").description("A sci-fi movie").imageUrl("url15").year(2023).genre(genre5).build();

        movieService.create(movie1);
        movieService.create(movie2);
        movieService.create(movie3);
        movieService.create(movie4);
        movieService.create(movie5);
        movieService.create(movie6);
        movieService.create(movie7);
        movieService.create(movie8);
        movieService.create(movie9);
        movieService.create(movie10);
        movieService.create(movie11);
        movieService.create(movie12);
        movieService.create(movie13);
        movieService.create(movie14);
        movieService.create(movie15);    
    }
}
