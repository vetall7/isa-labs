package org.example.movie.entites.Genre.listeners;

import org.example.movie.entites.Genre.functions.PutGenreRequestFunction;
import org.example.movie.entites.Genre.services.api.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageListener {

    private final GenreService genreService;

    private final PutGenreRequestFunction putGenreRequestFunction;

    @Autowired
    public KafkaMessageListener(GenreService genreService, PutGenreRequestFunction putGenreRequestFunction) {
        this.genreService = genreService;
        this.putGenreRequestFunction = putGenreRequestFunction;
    }

    @KafkaListener(topics = "delete-genre", groupId = "group-movie")
    public void deleteGenreMessage(String message) {
        System.out.println("Received Message in group group-movie: " + message);
        this.genreService.delete(message);
    }

    @KafkaListener(topics = "create-genre", groupId = "group-movie")
    public void createGenreMessage(String message) {
        System.out.println("Received Message in group group-movie: " + message);
        this.genreService.create(this.putGenreRequestFunction.apply(message));
    }
}
