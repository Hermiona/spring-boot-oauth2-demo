package com.meerim_task.demo.facade.dto;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@Value
public class UserDto {
    Long id;
    String username;
    String fullname;
}
