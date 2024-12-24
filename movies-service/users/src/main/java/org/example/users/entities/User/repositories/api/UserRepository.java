package org.example.users.entities.User.repositories.api;

import org.example.users.entities.User.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    void deleteByEmail(String email);

    Optional<User> findByName(String name);
}
