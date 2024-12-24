package org.example.users.entities.User.services.api;

import org.example.users.entities.User.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    void create(User user);

    void delete(String email);

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);

    Optional<User> findById(UUID id);

    List<User> findAll();
}
