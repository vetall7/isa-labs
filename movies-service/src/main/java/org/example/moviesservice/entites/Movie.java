package org.example.moviesservice.entites;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.Comparator;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class Movie implements Comparable<Movie>, Serializable {
    @Id
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