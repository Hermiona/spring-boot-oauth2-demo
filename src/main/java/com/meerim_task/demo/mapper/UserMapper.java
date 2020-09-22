package com.meerim_task.demo.mapper;

import com.meerim_task.demo.domain.User;
import com.meerim_task.demo.controller.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public interface UserMapper {
    UserDto toUserDto(User user);
}

@RequiredArgsConstructor
@Service
class DefaultUserMapper implements UserMapper {
    private final UserBalanceMapper userBalanceMapper;

    @Override
    public UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getFullname());
    }

}

