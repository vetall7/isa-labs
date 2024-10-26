package org.example.moviesservice.cmd;

import org.example.moviesservice.entites.Genre.Genre;
import org.example.moviesservice.entites.Genre.services.api.GenreService;
import org.example.moviesservice.entites.Movie.Movie;
import org.example.moviesservice.entites.Movie.services.api.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.UUID;

@Component
public class ApplicationCommand implements CommandLineRunner {
    private final MovieService movieService;

    private final GenreService genreService;

    @Autowired
    public ApplicationCommand(MovieService movieService, GenreService genreService) {
        this.movieService = movieService;
        this.genreService = genreService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String command;
        while (true) {
            command = scanner.next();
            switch (command) {
                case "exit":
                    break;
                case "help":
                    System.out.println("Available commands:");
                    System.out.println("exit - to exit the program");
                    System.out.println("help - to display this message");
                    System.out.println("movies - to display all movies");
                    System.out.println("genres - to display all genres");
                    System.out.println("add-movie - to add a movie");
                    System.out.println("add-genre - to add a genre");
                    break;
                case "movies":
                    movieService.findAll().forEach(System.out::println);
                    break;
                case "genres":
                    genreService.findAll().forEach(System.out::println);
                    break;
                case "add-movie":
                    UUID id = UUID.randomUUID();
                    System.out.println("Enter title:");
                    String title = scanner.next();
                    System.out.println("Enter genre:");
                    String genre_name = scanner.next();
                    System.out.println("Enter year:");
                    int year = scanner.nextInt();
                    System.out.println("Enter description:");
                    String description = scanner.next();
                    try {
                        Movie movie = Movie.builder()
                                .id(id)
                                .title(title)
                                .genre(genreService.findByName(genre_name).orElseThrow())
                                .year(year)
                                .description(description)
                                .build();
                        movieService.create(movie);
                    } catch (Exception e) {
                        System.out.println("Genre not found");
                    }
                    break;
                case "add-genre":
                    System.out.println("Enter name:");
                    String name = scanner.next();
                    System.out.println("Enter description:");
                    String genreDescription = scanner.next();
                    Genre genre = Genre.builder()
                            .name(name)
                            .description(genreDescription)
                            .build();
                    genreService.create(genre);
                    break;
                case "delete-movie":
                    UUID movieId = UUID.fromString(scanner.next());
                    movieService.delete(movieId);
                    break;
                default:
                    System.out.println("Unknown command. Type 'help' to display available commands.");
            }
        }
    }
}
