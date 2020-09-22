package com.meerim_task.demo.facade;

import com.meerim_task.demo.domain.UserBalance;
import com.meerim_task.demo.controller.dto.PageResponseDto;
import com.meerim_task.demo.controller.dto.UserBalanceSimplifiedDto;
import com.meerim_task.demo.mapper.UserBalanceMapper;
import com.meerim_task.demo.controller.dto.pageResponseDtoBuilder.CollectionToPageResponseDtoConverter;
import com.meerim_task.demo.controller.dto.pageResponseDtoBuilder.PageResponseDtoBuilder;
import com.meerim_task.demo.service.UserBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface UserBalanceFacade {
    PageResponseDto<UserBalanceSimplifiedDto> search();
}
@RequiredArgsConstructor
@Service
class DefaultUserBalanceFacade implements UserBalanceFacade{
    private final UserBalanceService userBalanceService;
    private final UserBalanceMapper userBalanceMapper;

    @Transactional(readOnly = true)
    @Override
    public PageResponseDto<UserBalanceSimplifiedDto> search() {
        Collection<UserBalance> userBalances= userBalanceService.search();
        PageResponseDtoBuilder pageResponseDtoConverter = new CollectionToPageResponseDtoConverter<UserBalance, UserBalanceSimplifiedDto>(userBalances, userBalanceMapper::toUserBalanceSimplifiedDto);
        return pageResponseDtoConverter.build();
    }
}