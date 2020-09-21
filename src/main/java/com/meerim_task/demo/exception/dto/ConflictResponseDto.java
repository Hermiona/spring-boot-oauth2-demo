package com.meerim_task.demo.exception.dto;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@Value
public class ConflictResponseDto {
    String message;
}
