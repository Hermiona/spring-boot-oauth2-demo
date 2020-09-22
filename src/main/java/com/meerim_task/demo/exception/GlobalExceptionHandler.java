package com.meerim_task.demo.exception;

import com.meerim_task.demo.exception.dto.ConflictResponseDto;
import com.meerim_task.demo.exception.dto.NotFoundResponseDto;
import com.meerim_task.demo.exception.dto.UnprocessableEntityErrorResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final String RESOURCE_NOT_FOUND = "Resource not found";
    private final String CONFLICT = "An error occurred, please try again later";
    private final String BAD_REQUEST = "Bad request";
    private final String UNPROCESSABLE = "Unprocessable entity";
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

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ConstraintViolationException.class)
    UnprocessableEntityErrorResponseDto handleConstraintViolationException(ConstraintViolationException exc) {
        log.warn(exc.getMessage());
        return new UnprocessableEntityErrorResponseDto(!StringUtils.isEmpty(exc.getMessage()) ? exc.getMessage() : UNPROCESSABLE);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    UnprocessableEntityErrorResponseDto handleMethodArgumentNotValidException(MethodArgumentNotValidException exc) {
        log.warn(exc.getMessage());
        return new UnprocessableEntityErrorResponseDto(!StringUtils.isEmpty(exc.getBindingResult().getFieldError().getDefaultMessage()) ?
                exc.getBindingResult().getFieldError().getDefaultMessage() : UNPROCESSABLE);
    }
}
