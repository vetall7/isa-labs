package org.example.moviesservice.entites.Movie.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class GetMovieResponse {
    private String title;

    private int year;

    private String genreName;
}
