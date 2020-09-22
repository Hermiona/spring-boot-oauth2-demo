package com.meerim_task.demo.controller.dto;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@RequiredArgsConstructor
public class PageResponseDto<T> {
    Long totalCount;
    List<T> result;
    PageInfoDto paging;
}



