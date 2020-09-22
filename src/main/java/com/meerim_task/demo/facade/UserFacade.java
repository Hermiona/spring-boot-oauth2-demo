package com.meerim_task.demo.facade;

import com.meerim_task.demo.domain.User;
import com.meerim_task.demo.exception.NotFoundException;
import com.meerim_task.demo.controller.dto.UserDto;
import com.meerim_task.demo.mapper.UserMapper;
import com.meerim_task.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface UserFacade {
    UserDto get(Long id) throws NotFoundException;
}

@RequiredArgsConstructor
@Service
class DefaultUserFacade implements UserFacade {
    private final UserService userService;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    @Override
    public UserDto get(Long id) throws NotFoundException {
        User user = userService.findById(id);
        return userMapper.toUserDto(user);
    }
}
