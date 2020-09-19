package com.meerim_task.demo.mapper;

import com.meerim_task.demo.domain.User;
import com.meerim_task.demo.facade.dto.UserBalanceSimplifiedDto;
import com.meerim_task.demo.facade.dto.UserDto;
import com.meerim_task.demo.facade.dto.UserDtoWithBalance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

public interface UserMapper {
    UserDto toUserDto(User user);

    UserDtoWithBalance toUserDtoWithBalance(User user);
}

@RequiredArgsConstructor
@Service
class DefaultUserMapper implements UserMapper {
    private final UserBalanceMapper userBalanceMapper;

    @Override
    public UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getFullname());
    }

    @Override
    public UserDtoWithBalance toUserDtoWithBalance(User user) {
        Set<UserBalanceSimplifiedDto> userBalanceDtos = user.getBalances().stream().map(userBalanceMapper::toUserBalanceSimplifiedDto).collect(Collectors.toSet());
        return new UserDtoWithBalance(user.getId(), user.getUsername(), user.getFullname(), userBalanceDtos);
    }
}

