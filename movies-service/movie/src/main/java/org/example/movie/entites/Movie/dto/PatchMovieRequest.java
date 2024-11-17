package org.example.movie.entites.Movie.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class PatchMovieRequest {
    private String description;

    private String imageUrl;
}
