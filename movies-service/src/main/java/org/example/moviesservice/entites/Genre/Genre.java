package org.example.moviesservice.entites.Genre;

import jakarta.persistence.*;
import lombok.*;
import org.example.moviesservice.entites.Movie.Movie;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "genres")
public class Genre implements Serializable {
    @Id
    private String name;

    private String description;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "genre", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movie> movies;
}