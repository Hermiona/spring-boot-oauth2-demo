package com.meerim_task.demo.service;

import com.meerim_task.demo.domain.User;
import com.meerim_task.demo.domain.UserBalance;
import com.meerim_task.demo.exception.NotFoundException;
import com.meerim_task.demo.repository.UserBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface UserBalanceService {
    UserBalance findById(Long id) throws NotFoundException;

    UserBalance findByIdAndUser(Long id, User user) throws NotFoundException;

    Collection<UserBalance> search();
}

@RequiredArgsConstructor
@Service
class DefaultUserBalanceService implements UserBalanceService {
    private final UserBalanceRepository userBalanceRepository;

    @Transactional(readOnly = true)
    @Override
    public UserBalance findById(Long id) throws NotFoundException {
        return userBalanceRepository.findById(id).orElseThrow(() -> new NotFoundException(UserBalance.class, Pair.of("id" , id)));
    }

    @Transactional(readOnly = true)
    @Override
    public UserBalance findByIdAndUser(Long id, User user) throws NotFoundException {
        return userBalanceRepository.findByIdAndUser(id, user).orElseThrow(() -> new NotFoundException(UserBalance.class, Pair.of("id", id), Pair.of("userId", user.getId())));
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<UserBalance> search() {
        return userBalanceRepository.findAll();
    }
}
