package org.example.entities;


import lombok.*;

import java.io.Serializable;
import java.util.Comparator;
import java.util.UUID;

@EqualsAndHashCode
@Getter
@Setter
@Builder
@ToString
public class Movie implements Comparable<Movie>, Serializable {
    private UUID id;

    private String title;

    private String description;

    private String imageUrl;

    private int year;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Genre genre;

    @Override
    public int compareTo(Movie o) {
        return this.getTitle().compareTo(o.getTitle());
    }

    public static Comparator<Movie> byYear() {
        return Comparator.comparingInt(Movie::getYear);
    }
}
