package org.example.entities;

import lombok.*;

import java.util.Comparator;
import java.util.UUID;

@EqualsAndHashCode
@Getter
@Setter
@Builder
@ToString
public class Award implements Comparable<Award> {
    private UUID id;

    private String name;

    private String category;

    private int year;

    private Movie movie;

    @Override
    public int compareTo(Award o) {
        return this.getName().compareTo(o.getName());
    }

    public static Comparator<Award> byYear() {
        return Comparator.comparingInt(Award::getYear);
    }
}
