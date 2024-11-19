package org.example.genre.Genre.services.impl;

import org.example.genre.Genre.Genre;
import org.example.genre.Genre.event.repository.api.GenreEventRepository;
import org.example.genre.Genre.repositories.api.GenreRepository;
import org.example.genre.Genre.services.api.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreDefaultService implements GenreService{
    private final GenreRepository genreRepository;

    private final GenreEventRepository genreEventRepository;

    @Autowired
    public GenreDefaultService(GenreRepository genreRepository, GenreEventRepository genreEventRepository) {
        this.genreRepository = genreRepository;
        this.genreEventRepository = genreEventRepository;
    }

    @Override
    public Optional<Genre> findByName(String name) {
        return genreRepository.findById(name);
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public void create(Genre genre, Boolean isReported) {
        genreRepository.save(genre);
        if (isReported) {
            genreEventRepository.save(genre.getName());
        }
    }

    @Override
    public void update(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public void delete(String name) {
        genreRepository.deleteById(name);
        genreEventRepository.delete(name);
    }
}
