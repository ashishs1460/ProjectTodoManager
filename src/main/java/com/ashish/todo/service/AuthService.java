package com.ashish.todo.service;

import com.ashish.todo.dto.AuthenticationRequest;
import com.ashish.todo.dto.AuthenticationResponse;
import com.ashish.todo.dto.RegistrationRequest;

public interface AuthService {
    void registerUser(RegistrationRequest user);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
