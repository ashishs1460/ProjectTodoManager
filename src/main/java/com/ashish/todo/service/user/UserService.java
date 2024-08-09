package com.ashish.todo.service.user;

import com.ashish.todo.dto.AuthenticationRequest;
import com.ashish.todo.dto.AuthenticationResponse;
import com.ashish.todo.dto.RegistrationRequest;
import com.ashish.todo.model.User;

public interface UserService {
    void registerUser(RegistrationRequest user);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
