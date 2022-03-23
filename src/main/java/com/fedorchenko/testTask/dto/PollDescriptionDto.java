package com.fedorchenko.testTask.dto;

import java.io.Serializable;

public class PollDescriptionDto implements Serializable {
    private final String name;
    private final String description;

    public PollDescriptionDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
