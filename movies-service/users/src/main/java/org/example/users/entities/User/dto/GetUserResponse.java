package org.example.users.entities.User.dto;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class GetUserResponse {
    private String name;
    private String email;
    private String createdAt;
    private String updatedAt;
    private UUID id;
}
