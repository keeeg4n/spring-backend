package com.example.spring_redis.service;

import com.example.spring_redis.model.User;
import com.example.spring_redis.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User addUser(User user) {
        return repository.save(user);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUserById(Long userId) {
        return repository.findById(userId).orElseThrow();
    }

    public String deleteUser(Long userId) {
        User user = repository.findById(userId).orElseThrow();
        repository.delete(user);
        return "Deleted user";
    }

}
