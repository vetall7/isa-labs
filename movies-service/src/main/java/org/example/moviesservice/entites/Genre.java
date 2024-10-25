package org.example.moviesservice.entites;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Builder
@EqualsAndHashCode
@ToString
@Setter
@Getter
public class Genre implements Serializable {
    private String name;

    private String description;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Movie> movies;

}