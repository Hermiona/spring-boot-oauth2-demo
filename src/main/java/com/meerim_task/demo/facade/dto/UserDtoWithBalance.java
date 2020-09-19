package com.meerim_task.demo.facade.dto;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.Collection;

@RequiredArgsConstructor
@Value
public class UserDtoWithBalance {
    Long id;
    String username;
    String fullname;
    Collection<UserBalanceSimplifiedDto> balances;
}
