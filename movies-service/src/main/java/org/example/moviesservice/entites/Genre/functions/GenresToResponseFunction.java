package org.example.moviesservice.entites.Genre.functions;

import org.example.moviesservice.entites.Genre.Genre;
import org.example.moviesservice.entites.Genre.dto.GetGenresResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class GenresToResponseFunction implements Function<List<Genre>, GetGenresResponse> {
    @Override
    public GetGenresResponse apply(List<Genre> entities){
        return GetGenresResponse.builder()
                .genres(entities.stream()
                        .map(
                                genre -> GetGenresResponse.Genre.builder()
                                        .name(genre.getName())
                                        .build()
                        ).toList())
                .build();
    }
}
