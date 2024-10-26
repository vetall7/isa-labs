package org.example.moviesservice.entites.Movie.dto;

import lombok.*;

import java.util.Comparator;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class MovieDto implements Comparable<MovieDto> {
    private String title;

    private String description;

    private String imageUrl;

    private int year;

    private String genreName;

    @Override
    public int compareTo(MovieDto o) {
        return this.getTitle().compareTo(o.getTitle());
    }

    public static Comparator<MovieDto> byYear() {
        return Comparator.comparingInt(MovieDto::getYear);
    }
}