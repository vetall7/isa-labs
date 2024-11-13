package org.example.moviesservice.entites.Genre.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PatchGenreRequest {
    private String description;
}
