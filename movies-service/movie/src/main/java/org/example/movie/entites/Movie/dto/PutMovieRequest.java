package org.example.movie.entites.Movie.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class PutMovieRequest {
    private String title;

    private String description;

    private String imageUrl;

    private int year;

    private String genreName;
}
