package org.example.users.entities.User.controllers.api;

import org.example.users.entities.User.dto.GetUserResponse;
import org.example.users.entities.User.dto.GetUsersResponse;
import org.example.users.entities.User.dto.PutUserRequest;
import org.example.users.entities.User.dto.UserLoginInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface UserController {
    @PutMapping("/api/users/registration/{id}")
    @ResponseStatus(HttpStatus.OK)
    void createUser(
            @PathVariable
            UUID id,
            @RequestBody
            PutUserRequest putUserRequest
    );

    @DeleteMapping("/api/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteUser(
            @PathVariable
            UUID id
    );

    @GetMapping("/api/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    GetUserResponse getUser(
            @PathVariable
            UUID id
    );

    @GetMapping("/api/users")
    @ResponseStatus(HttpStatus.OK)
    GetUsersResponse getUsers();

    @PostMapping("api/users/login")
    @ResponseStatus(HttpStatus.OK)
    String login(@RequestBody UserLoginInfo loginInfo);
}
