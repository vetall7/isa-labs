package org.example.users.entities.User.functions;

import org.example.users.entities.User.User;
import org.example.users.entities.User.dto.GetUserResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserToResponseFunction implements Function<User, GetUserResponse> {

    @Override
    public GetUserResponse apply(User user) {
        return GetUserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt().toString())
                .updatedAt(user.getUpdatedAt().toString())
                .build();
    }
}
