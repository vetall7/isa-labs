package org.example.users.entities.User.functions;

import org.example.users.entities.User.User;
import org.example.users.entities.User.dto.PutUserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class RequestToUserFunction implements BiFunction<UUID, PutUserRequest, User> {

    private final PasswordEncoder passwordEncoder;

    public RequestToUserFunction(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User apply(UUID id, PutUserRequest putUserRequest) {
        return User.builder()
                .id(id)
                .name(putUserRequest.getName())
                .email(putUserRequest.getEmail())
                .password(this.passwordEncoder.encode(putUserRequest.getPassword()))
                .role(User.Role.ADMIN)
                .build();
    }
}
