package org.example.moviesservice.entites.Genre.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class GetGenreResponse {
    private String name;

    private String description;
}
