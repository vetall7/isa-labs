package org.example.genre.Genre.event.repository.impl;

import org.example.genre.Genre.event.repository.api.GenreEventRepository;
import org.hibernate.annotations.DialectOverride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class ProfessionEventRestRepository implements GenreEventRepository {
    private final RestTemplate restTemplate;

    @Autowired
    public ProfessionEventRestRepository(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public void delete(String name){
        this.restTemplate.delete("/api/genres/{name}", name);
    }

    @Override
    public void save(String name) { this.restTemplate.put("/api/genres/{name}", name, String.class); }
}
