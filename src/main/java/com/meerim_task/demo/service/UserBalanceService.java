package com.meerim_task.demo.service;

import com.meerim_task.demo.domain.UserBalance;
import com.meerim_task.demo.exception.NotFoundException;
import com.meerim_task.demo.repository.UserBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface UserBalanceService {
    UserBalance findById(Long id) throws NotFoundException;

    Page<UserBalance> search(Pageable p);
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
    public Page<UserBalance> search(Pageable p) {
        return userBalanceRepository.findAll(p);
    }
}
