package org.example.users.entities.User.services.impl;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.users.entities.User.JWT.JwtUtil;
import org.example.users.entities.User.User;
import org.example.users.entities.User.dto.UserLoginInfo;
import org.example.users.entities.User.repositories.api.UserRepository;
import org.example.users.entities.User.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final JwtUtil jwtTokenUtil;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDefaultService(UserRepository userRepository, JwtUtil jwtTokenUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
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

    @Override
    public String login(UserLoginInfo loginInfo) {
        Optional<User> user = userRepository.findByName(loginInfo.getName());
        if (user.isEmpty()) {
            return "";
        }
        User unpackedUser = user.get();
        if (passwordEncoder.matches(loginInfo.getPassword(), unpackedUser.getPassword())) {
            return this.jwtTokenUtil.generateToken(unpackedUser.getName(), unpackedUser.getRole().toString());
        } else {
            return "";
        }
    }
}
