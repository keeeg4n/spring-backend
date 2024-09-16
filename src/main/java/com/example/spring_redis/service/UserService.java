package com.example.spring_redis.service;

import com.example.spring_redis.model.User;
import com.example.spring_redis.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserRepository repository;

    private final RedisService redisService;

    public UserService(UserRepository repository, RedisService redisService) {
        this.repository = repository;
        this.redisService = redisService;
    }

    public User addUser(User user) {
        return repository.save(user);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUserById(Long userId) {
        User redisResponse = redisService.get(userId.toString(), User.class);
        if (Objects.isNull(redisResponse)) {
            User u = repository.findById(userId).orElseThrow();
            redisService.set(userId.toString(), u, 60L);
            return u;
        } else {
            return redisResponse;
        }
    }

    public String deleteUser(Long userId) {
        User user = repository.findById(userId).orElseThrow();
        repository.delete(user);
        return "Deleted user";
    }

}
