package org.example.entities;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class AwardDto {
    private UUID id;

    private String name;

    private String category;

    private int year;

    private String movieTitle;
}
