package com.ashish.todo.service.user;

import com.ashish.todo.configuration.jwt.JwtService;
import com.ashish.todo.dto.AuthenticationRequest;
import com.ashish.todo.dto.AuthenticationResponse;
import com.ashish.todo.dto.RegistrationRequest;
import com.ashish.todo.exceptionHandling.UserAlreadyExistException;
import com.ashish.todo.model.User;
import com.ashish.todo.respository.UserRepository;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    @Autowired
    public UserServiceImp(UserRepository userRepository,
                            PasswordEncoder passwordEncoder,
                            AuthenticationManager authenticationManager,
                            JwtService jwtService){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    @Override
    public void registerUser(RegistrationRequest user) {
        String email = user.getEmail();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()){
            throw new UserAlreadyExistException("user with email "+email+" already exists");
        }else {
            User newUser = User.builder()
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .build();
            userRepository.save(newUser);
        }

    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var claims = new HashMap<String,Object>();
        var user = ((User) auth.getPrincipal());
        claims.put("fullName",user.fullName());
        var jwt = jwtService.generateToken(claims,user);
        return AuthenticationResponse
                .builder()
                .token(jwt)
                .user(user)
                .build();
    }

    @Override
    public Optional<User> findById(Integer userId) {
        return userRepository.findById(userId);
    }
}
