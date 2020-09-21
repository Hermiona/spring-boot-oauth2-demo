package com.meerim_task.demo.service;

import com.meerim_task.demo.domain.User;
import com.meerim_task.demo.exception.NotFoundException;
import com.meerim_task.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface UserService {
    User findByUsername(String username) throws NotFoundException;

    User findById(Long id) throws NotFoundException;
}

@RequiredArgsConstructor
@Service
class DefaultUserService implements UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public User findByUsername(String username) throws NotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(User.class, Pair.of("username", username)));
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(Long id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(User.class, Pair.of("id", id)));
    }
}
