package org.example.users.entities.User.controllers.impl;

import org.example.users.entities.User.controllers.api.UserController;
import org.example.users.entities.User.dto.GetUserResponse;
import org.example.users.entities.User.dto.GetUsersResponse;
import org.example.users.entities.User.dto.PutUserRequest;
import org.example.users.entities.User.dto.UserLoginInfo;
import org.example.users.entities.User.functions.RequestToUserFunction;
import org.example.users.entities.User.functions.UserToResponseFunction;
import org.example.users.entities.User.functions.UsersToResponseFunction;
import org.example.users.entities.User.services.api.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
public class UserDefaultController implements UserController {
    private final UserService userService;

    private final RequestToUserFunction requestToMovieFunction;

    private final UserToResponseFunction userToResponseFunction;

    private final UsersToResponseFunction usersToResponseFunction;


    public UserDefaultController(UserService userService, RequestToUserFunction requestToMovieFunction, UserToResponseFunction userToResponseFunction, UsersToResponseFunction usersToResponseFunction) {
        this.userService = userService;
        this.requestToMovieFunction = requestToMovieFunction;
        this.userToResponseFunction = userToResponseFunction;
        this.usersToResponseFunction = usersToResponseFunction;
    }

    @Override
    public void createUser(UUID id, PutUserRequest putUserRequest) {
        userService.findByName(putUserRequest.getName())
                .ifPresentOrElse(
                        user -> {
                            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
                        },
                        () -> userService.create(this.requestToMovieFunction.apply(id, putUserRequest))

                );
    }

    @Override
    public void deleteUser(UUID id) {
        userService.findById(id)
                .ifPresentOrElse(
                        user -> userService.delete(user.getEmail()),
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
                        }
                );
    }

    @Override
    public GetUsersResponse getUsers() {
        return usersToResponseFunction.apply(userService.findAll());
    }

    @Override
    public String login(UserLoginInfo loginInfo) {
        String jwt_token = userService.login(loginInfo);
        if (jwt_token.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }else{
            return jwt_token;
        }
    }

    @Override
    public GetUserResponse getUserByName(String name) {
        return userService.findByName(name).map(userToResponseFunction)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}
