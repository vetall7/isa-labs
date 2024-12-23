package org.example.users.entities.User.repositories.api;

import org.example.users.entities.User.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface userRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
