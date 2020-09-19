package com.meerim_task.demo.facade;

import com.meerim_task.demo.domain.UserBalance;
import com.meerim_task.demo.facade.dto.PageResponseDto;
import com.meerim_task.demo.facade.dto.UserBalanceSimplifiedDto;
import com.meerim_task.demo.mapper.UserBalanceMapper;
import com.meerim_task.demo.mapper.pageResponseDtoBuilder.PageResponseDtoBuilder;
import com.meerim_task.demo.mapper.pageResponseDtoBuilder.PageToPageResponseDtoConverter;
import com.meerim_task.demo.service.UserBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Pageable p= PageRequest.of(0, 5);
        Page<UserBalance> page= userBalanceService.search(p);
        PageResponseDtoBuilder pageResponseDtoConverter = new PageToPageResponseDtoConverter<UserBalance, UserBalanceSimplifiedDto>(page, userBalanceMapper::toUserBalanceSimplifiedDto);
        return pageResponseDtoConverter.build();
    }
}