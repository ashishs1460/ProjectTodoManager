package com.ashish.todo.service.user;

import com.ashish.todo.dto.AuthenticationRequest;
import com.ashish.todo.dto.AuthenticationResponse;
import com.ashish.todo.dto.RegistrationRequest;
import com.ashish.todo.model.User;

import java.util.Optional;

public interface UserService {
    void registerUser(RegistrationRequest user);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    Optional<User> findById(Integer userId);
}
