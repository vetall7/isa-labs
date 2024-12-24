package org.example.users.entities.User.services.impl;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.users.entities.User.User;
import org.example.users.entities.User.repositories.api.UserRepository;
import org.example.users.entities.User.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@Builder
@Service
public class UserDefaultService implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserDefaultService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(String email) {
        userRepository.deleteByEmail(email);
    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }
}
