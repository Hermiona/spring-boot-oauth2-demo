package com.meerim_task.demo.service;

import com.meerim_task.demo.domain.UserBalance;
import com.meerim_task.demo.exception.NotFoundException;
import com.meerim_task.demo.repository.UserBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface UserBalanceService {
    UserBalance findById(Long id) throws NotFoundException;

    Collection<UserBalance> search();
}

@RequiredArgsConstructor
@Service
class DefaultUserBalanceService implements UserBalanceService {
    private final UserBalanceRepository userBalanceRepository;

    @Transactional(readOnly = true)
    @Override
    public UserBalance findById(Long id) throws NotFoundException {
        return userBalanceRepository.findById(id).orElseThrow(() -> new NotFoundException(UserBalance.class, "id", id));
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<UserBalance> search() {
        return userBalanceRepository.findAll();
    }
}
