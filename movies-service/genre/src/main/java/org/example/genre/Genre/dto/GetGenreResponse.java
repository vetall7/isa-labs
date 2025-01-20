package org.example.genre.Genre.dto;

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
