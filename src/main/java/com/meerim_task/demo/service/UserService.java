package com.meerim_task.demo.service;

import com.meerim_task.demo.domain.User;
import com.meerim_task.demo.exceptions.NotFoundException;
import com.meerim_task.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface UserService {
    User findByUsername(String username) throws NotFoundException;
}

@RequiredArgsConstructor
@Service
class DefaultUserService implements UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public User findByUsername(String username) throws NotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(User.class, "username", username));
    }
}
