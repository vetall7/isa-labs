package org.example.moviesservice.entites.Genre.services.impl;

import org.example.moviesservice.entites.Genre.Genre;
import org.example.moviesservice.entites.Genre.repositories.api.GenreRepository;
import org.example.moviesservice.entites.Genre.services.api.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreDefaultService implements GenreService{
    private final GenreRepository genreRepository;

    @Autowired
    public GenreDefaultService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
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
    public Genre create(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Genre update(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public void delete(String name) {
        genreRepository.deleteById(name);
    }
}
