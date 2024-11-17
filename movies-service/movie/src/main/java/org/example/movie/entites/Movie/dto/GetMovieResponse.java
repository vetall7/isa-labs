package org.example.movie.entites.Movie.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class GetMovieResponse {
    private String title;

    private String description;

    private String imageUrl;

    private String genreName;
}
