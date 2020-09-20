package com.meerim_task.demo.facade.dto.pageResponseDtoBuilder;

import com.meerim_task.demo.facade.dto.PageResponseDto;

public interface PageResponseDtoBuilder<R> {
    PageResponseDto<R> build();
}

