package com.meerim_task.demo.exception;

import org.springframework.data.util.Pair;

import java.util.Arrays;
import java.util.stream.Collectors;

public class NotFoundException extends Exception {

    public NotFoundException(Class objectType, Pair<String, Object>... typeValues) {
        super("Failed to search %1$s with %2$s".formatted(objectType.getSimpleName(), buildMessage(typeValues)));
    }

    private static String buildMessage(Pair<String, Object>... typeValues){
        return Arrays.stream(typeValues).map(item -> "%1$s: %2$s".formatted(item.getFirst(), item.getSecond())).collect(Collectors.joining(", "));
    }
}

