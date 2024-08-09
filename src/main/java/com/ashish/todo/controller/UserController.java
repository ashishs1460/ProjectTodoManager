package com.ashish.todo.controller;

import com.ashish.todo.model.User;
import com.ashish.todo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/getUser/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Integer userId) {
        Optional<User> optionalUser = userService.findById(userId);
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if user not found
        }
    }
}
