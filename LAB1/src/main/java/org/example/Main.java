package org.example;

import org.example.entities.Genre;
import org.example.entities.Movie;
import org.example.entities.MovieDto;
import org.example.entities.MovieToDto;

import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // 2
        System.out.println("2");
        Movie movie1 = Movie.builder()
                .title("The Shawshank Redemption")
                .description("Two imprisoned")
                .imageUrl("https://www.imdb.com/title/tt0111161/mediaviewer/rm10105600/")
                .year(1994)
                .genre(null)
                .build();

        Movie movie2 = Movie.builder()
                .title("The Godfather")
                .description("The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant youngest son.")
                .imageUrl("https://www.imdb.com/title/tt0068646/mediaviewer/rm10105600/")
                .year(1972)
                .genre(null)
                .build();

        Movie movie3 = Movie.builder()
                .title("The Dark Knight")
                .description("When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.")
                .imageUrl("https://www.imdb.com/title/tt0468569/mediaviewer/rm10105600/")
                .year(2008)
                .genre(null)
                .build();

        Movie movie4 = Movie.builder()
                .title("The Godfather: Part II")
                .description("The early life and career of Vito Corleone in 1920s New York City is portrayed, while his son, Michael, expands and tightens his grip on the family crime syndicate.")
                .imageUrl("https://www.imdb.com/title/tt0071562/mediaviewer/rm10105600/")
                .year(1974)
                .genre(null)
                .build();

        Movie movie5 = Movie.builder()
                .title("The Lord of the Rings: The Return of the King")
                .description("Gandalf and Aragorn lead the")
                .imageUrl("https://www.imdb.com/title/tt0167260/mediaviewer/rm10105600/")
                .year(2003)
                .genre(null)
                .build();


        Genre genre1 = Genre.builder()
                .name("Comedy")
                .description("A genre of film in which the main emphasis is on humor.")
                .movies(List.of(movie1, movie2))
                .build();

        movie1.setGenre(genre1);
        movie2.setGenre(genre1);

        Genre genre2 = Genre.builder()
                .name("Action")
                .description("A genre of film in which the main emphasis is on exciting action.")
                .movies(List.of(movie3, movie4))
                .build();

        movie3.setGenre(genre2);
        movie4.setGenre(genre2);

        Genre genre3 = Genre.builder()
                .name("Adventure")
                .description("A genre of film in which the main emphasis is on adventure.")
                .movies(List.of(movie5))
                .build();

        movie5.setGenre(genre3);

        List<Genre> genres = List.of(genre1, genre2, genre3);

        printGenres(genres);

        // 3
        System.out.println("3");
        Set<Movie> movies = genres.stream()
                .flatMap(genre -> genre.getMovies().stream())
                .collect(Collectors.toSet());

        movies.forEach(System.out::println);

        // 4
        System.out.println("4");
        movies.stream()
                .filter(movie -> movie.getYear() > 1990)
                .sorted(Movie.byYear())
                .forEach(System.out::println);

        // 5
        System.out.println("5");
        MovieToDto converter = new MovieToDto();

        List<MovieDto> moviesDto = movies.stream()
                .map(movie -> converter.apply(movie))
                .sorted(MovieDto.byYear())
                .collect(Collectors.toList());

        moviesDto.forEach(System.out::println);

        // 6
        System.out.println("6");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("genres.ser"))) {
            oos.writeObject(genres);
            System.out.println("Genres have been serialized to genres.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("genres.ser"))) {
            List<Genre> deserializedGenres = (List<Genre>) ois.readObject();
            System.out.println("Genres have been deserialized from genres.ser");
            printGenres(deserializedGenres);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 7
        System.out.println("7");

        ForkJoinPool customThreadPool = new ForkJoinPool(2);

        try {
            customThreadPool.submit(() -> {
                genres.parallelStream().forEach(genre -> {
                    genre.getMovies().parallelStream().forEach(movie -> {
                        try {
                            System.out.println(Thread.currentThread().getName() + " - " + movie);
                            Thread.sleep(1000); // Simulate workload
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    });
                });
            }).get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            customThreadPool.shutdown();
            try {
                if (!customThreadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                    customThreadPool.shutdownNow();
                }
            } catch (InterruptedException e) {
                customThreadPool.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }

    }

    private static void printGenres(List<Genre> genres) {
        genres.forEach(genre -> {
            System.out.println(genre);
            genre.getMovies().forEach(movie -> {
                System.out.println("\t" + movie);
            });
        });
    }
}