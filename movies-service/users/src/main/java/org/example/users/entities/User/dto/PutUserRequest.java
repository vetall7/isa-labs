package org.example.users.entities.User.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PutUserRequest {
    private String name;
    private String email;
    private String password;
}
