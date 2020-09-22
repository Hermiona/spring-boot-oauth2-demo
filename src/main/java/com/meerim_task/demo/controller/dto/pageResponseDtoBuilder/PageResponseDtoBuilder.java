package com.meerim_task.demo.controller.dto.pageResponseDtoBuilder;

import com.meerim_task.demo.controller.dto.PageResponseDto;

public interface PageResponseDtoBuilder<R> {
    PageResponseDto<R> build();
}

