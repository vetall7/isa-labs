package org.example.users.entities.User.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUsersResponse {

    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Builder
    @EqualsAndHashCode
    public static class User {
        private String name;
        private String email;
        private UUID id;
    }

    @Singular
    private List<User> users;
}
