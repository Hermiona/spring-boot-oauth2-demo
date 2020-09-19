package com.meerim_task.demo.mapper;

import com.meerim_task.demo.domain.UserBalance;
import com.meerim_task.demo.facade.dto.UserBalanceSimplifiedDto;
import org.springframework.stereotype.Service;

public interface UserBalanceMapper {
    UserBalanceSimplifiedDto toUserBalanceSimplifiedDto(UserBalance userBalance);
}

@Service
class DefaultUserBalanceMapper implements UserBalanceMapper {

    @Override
    public UserBalanceSimplifiedDto toUserBalanceSimplifiedDto(UserBalance userBalance) {
        return new UserBalanceSimplifiedDto(userBalance.getId(), userBalance.getBalance());
    }
}
