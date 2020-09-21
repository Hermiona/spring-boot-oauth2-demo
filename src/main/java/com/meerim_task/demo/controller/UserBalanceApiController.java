package com.meerim_task.demo.controller;

import com.meerim_task.demo.facade.UserBalanceFacade;
import com.meerim_task.demo.facade.dto.PageResponseDto;
import com.meerim_task.demo.facade.dto.UserBalanceSimplifiedDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user/{userId}/user_balances")
public class UserBalanceApiController {
    private final UserBalanceFacade userBalanceFacade;

    @GetMapping
    public PageResponseDto<UserBalanceSimplifiedDto> search() {
        return userBalanceFacade.search();
    }
}
