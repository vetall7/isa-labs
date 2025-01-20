package org.example.movie.entites.Movie;


import jakarta.persistence.*;
import lombok.*;
import org.example.movie.entites.Genre.Genre;

import java.io.Serializable;
import java.util.Comparator;
import java.util.UUID;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "movies")
public class Movie implements Comparable<Movie>, Serializable {
    @Id
    private UUID id;

    private String title;

    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "release_year")
    private int year;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "genre_name")
    private Genre genre;

    @Override
    public int compareTo(Movie o) {
        return this.getTitle().compareTo(o.getTitle());
    }

    public static Comparator<Movie> byYear() {
        return Comparator.comparingInt(Movie::getYear);
    }
}