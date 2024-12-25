package org.example.users.entities.User.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginInfo {
    private String name;
    private String password;
}
