package com.example.spring_redis.controller;

import com.example.spring_redis.model.User;
import com.example.spring_redis.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService service;

    UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User response = service.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> response = service.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        User response = service.getUserById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        String response = service.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
