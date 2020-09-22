package com.meerim_task.demo.controller.dto;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@Value
public class UserBalanceSimplifiedDto {
    Long id;
    Integer balance;
}
