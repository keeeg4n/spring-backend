package dev.keeg4n.spring_jwt.service;

import dev.keeg4n.spring_jwt.exception.UserNotFoundException;
import dev.keeg4n.spring_jwt.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(
                        () -> new UserNotFoundException("User not found")
                );
    }
}
