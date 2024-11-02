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
        main_loop:
        while (true) {
            command = scanner.next();
            scanner.skip("\n");
            switch (command) {
                case "exit":
                    break main_loop;
                case "help":
                    System.out.println("Available commands:");
                    System.out.println("exit - to exit the program");
                    System.out.println("help - to display this message");
                    System.out.println("movies - to display all movies");
                    System.out.println("genres - to display all genres");
                    System.out.println("add-movie - to add a movie");
                    System.out.println("add-genre - to add a genre");
                    System.out.println("delete-movie - to delete a movie");
                    System.out.println("find-by-genre - to find movies by genre");
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
                    String title = scanner.nextLine();
                    System.out.println("Enter genre:");
                    String genre_name = scanner.nextLine();
                    System.out.println("Enter year:");
                    int year = scanner.nextInt();
                    scanner.skip("\n");
                    System.out.println("Enter description:");
                    String description = scanner.nextLine();
                    try {
                        Movie movie = Movie.builder()
                                .title(title)
                                .id(id)
                                .year(year)
                                .description(description)
                                .build();
                        movieService.create(movie, genre_name);
                        System.out.println("Movie added successfully");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "add-genre":
                    System.out.println("Enter name:");
                    String name = scanner.nextLine();
                    System.out.println("Enter description:");
                    String genreDescription = scanner.nextLine();
                    Genre genre = Genre.builder()
                            .name(name)
                            .description(genreDescription)
                            .build();
                    genreService.create(genre);
                    System.out.println("Genre added successfully");
                    break;
                case "delete-movie":
                    System.out.println("Enter movie id:");
                    String movieId = scanner.next();
                    movieService.delete(UUID.fromString(movieId));
                    System.out.println("Movie deleted successfully");
                    break;
                case "find-by-genre":
                    System.out.println("Enter genre name:");
                    String genreName = scanner.next();
                    movieService.findAllByGenreName(genreName).forEach(System.out::println);
                    break;
                default:
                    System.out.println("Unknown command. Type 'help' to display available commands.");
            }
        }
    }
}
