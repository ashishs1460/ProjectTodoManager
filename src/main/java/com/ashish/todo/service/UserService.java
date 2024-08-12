package com.ashish.todo.service;

import com.ashish.todo.dto.AuthenticationRequest;
import com.ashish.todo.dto.AuthenticationResponse;
import com.ashish.todo.dto.RegistrationRequest;
import com.ashish.todo.model.User;

import java.util.Optional;

public interface UserService {


    Optional<User> findById(Integer userId);
}
