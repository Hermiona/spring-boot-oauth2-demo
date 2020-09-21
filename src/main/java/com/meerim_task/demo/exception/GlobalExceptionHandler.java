package com.meerim_task.demo.exception;

import com.meerim_task.demo.exception.dto.ConflictResponseDto;
import com.meerim_task.demo.exception.dto.NotFoundResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final String RESOURCE_NOT_FOUND = "Resource not found";
    private final String CONFLICT = "An error occurred, please try again later";
    private final String BAD_REQUEST = "Bad request";
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    NotFoundResponseDto handleNotFoundException(NotFoundException exc) {
        log.warn(exc.getMessage());
        return new NotFoundResponseDto(!StringUtils.isEmpty(exc.getMessage()) ? exc.getMessage() : RESOURCE_NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ConflictException.class)
    ConflictResponseDto handleConflictException(ConflictException exc) {
        log.warn(exc.getMessage());
        return new ConflictResponseDto(!StringUtils.isEmpty(exc.getMessage()) ? exc.getMessage() : CONFLICT);
    }

}
