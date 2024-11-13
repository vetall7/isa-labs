package org.example.moviesservice.entites.Genre.dto;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PutGenreRequest {
    private String description;
}
