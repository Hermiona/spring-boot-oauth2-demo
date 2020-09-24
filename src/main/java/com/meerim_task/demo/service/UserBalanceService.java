package com.meerim_task.demo.service;

import com.meerim_task.demo.domain.User;
import com.meerim_task.demo.domain.UserBalance;
import com.meerim_task.demo.exception.ConflictException;
import com.meerim_task.demo.exception.NotFoundException;
import com.meerim_task.demo.repository.UserBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface UserBalanceService {
    UserBalance getById(Long id) throws NotFoundException;

    UserBalance getByIdAndUser(Long id, User user) throws NotFoundException;

    Collection<UserBalance> search();

    UserBalance withdraw(UserBalance userBalance, Integer amount) throws ConflictException;

    UserBalance deposit(UserBalance userBalance, Integer amount);
}

@RequiredArgsConstructor
@Service
class DefaultUserBalanceService implements UserBalanceService {
    private final UserBalanceRepository userBalanceRepository;

    @Transactional(readOnly = true)
    @Override
    public UserBalance getById(Long id) throws NotFoundException {
        return userBalanceRepository.findById(id).orElseThrow(() -> new NotFoundException(UserBalance.class, Pair.of("id", id)));
    }

    @Transactional(readOnly = true)
    @Override
    public UserBalance getByIdAndUser(Long id, User user) throws NotFoundException {
        return userBalanceRepository.findByIdAndUser(id, user).orElseThrow(() -> new NotFoundException(UserBalance.class, Pair.of("id", id), Pair.of("userId", user.getId())));
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<UserBalance> search() {
        return userBalanceRepository.findAll();
    }

    @Transactional
    @Override
    public UserBalance withdraw(UserBalance userBalance, Integer amount) throws ConflictException {
        userBalance.withdraw(amount);
        return userBalanceRepository.save(userBalance);
    }

    @Transactional
    @Override
    public UserBalance deposit(UserBalance userBalance, Integer amount) {
        userBalance.deposit(amount);
        return userBalanceRepository.save(userBalance);
    }
}
