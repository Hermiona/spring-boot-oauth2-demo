package com.meerim_task.demo.exceptions;

public class NotFoundException extends Exception {
    public NotFoundException(Class objectType, String attributeType, Object attribute) {
        super("Failed to search %1$s with %2$s: %3$s".formatted(objectType.getSimpleName(), attributeType, attribute.toString()));
    }
}

