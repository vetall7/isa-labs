package org.example.moviesservice.entites.Genre.dto;

import java.util.List;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class GetGenresResponse {

    @Getter
    @Setter
    @Builder
    @ToString
    @EqualsAndHashCode
    public static class Genre {
        private String name;
    }

    @Singular
    private List<Genre> genres;
}
