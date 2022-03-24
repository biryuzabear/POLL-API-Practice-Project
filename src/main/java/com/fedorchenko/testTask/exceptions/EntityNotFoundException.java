package com.fedorchenko.testTask.exceptions;

import com.fedorchenko.testTask.enums.EntityList;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(EntityList exceptionIn, Long id) {
        super("Entity " + exceptionIn.toString() + "with id(" + id + ")was not find!");
    }
}
