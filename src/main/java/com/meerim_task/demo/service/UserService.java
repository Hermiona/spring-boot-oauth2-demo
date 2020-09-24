package com.meerim_task.demo.service;

import com.meerim_task.demo.domain.User;
import com.meerim_task.demo.exception.NotFoundException;
import com.meerim_task.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface UserService {
    User getByUsername(String username) throws NotFoundException;

    Optional<User> findByUsername(String username);

    User getById(Long id) throws NotFoundException;

    User add(User user);
}

@RequiredArgsConstructor
@Service
class DefaultUserService implements UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public User getByUsername(String username) throws NotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException(User.class, Pair.of("username", username)));
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    @Override
    public User getById(Long id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(User.class, Pair.of("id", id)));
    }

    @Transactional
    @Override
    public User add(User user) {
        return userRepository.save(user);
    }
}
